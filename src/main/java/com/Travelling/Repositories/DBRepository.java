package com.Travelling.Repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.Travelling.Repositories.Entities.PlaceTag;
import com.Travelling.Repositories.Entities.Tag;
import com.Travelling.Repositories.GooglePlace.GooglePlace;
import com.Travelling.Repositories.GooglePlace.Results;
import com.Travelling.Repositories.Entities.Place;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Repository
@Transactional
public class DBRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Gson gson;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private PlaceTagRepository placeTagRepository;

//	private Logger logger = LoggerFactory.getLogger(DBRepository.class);
	private final String api_key = "AIzaSyDuJNzA_7XO2m9DZcH16B9k4PRFUod3-ds";
	
	public List<String> getaddress(int pid) {
		String query_str = String.format("SELECT address FROM places WHERE pid=%d", pid);
		List<String> address = jdbcTemplate.query(query_str, (rs, rowNum)->rs.getString("address"));
		return address;				
	}
	
	public List<Place> getPlacefromTag(String tag){
		String query_str = String.format("%s%s%s",
				"SELECT places.pid, place_name, phone_num, website, address, city, state, country, postal_code, longitude, latitude\n"+
				"FROM travelling.places, travelling.placetag\n"+
				"WHERE places.pid = placetag.pid AND placetag.tid = (SELECT tid FROM travelling.tags WHERE tag=\"",tag,"\");");
		List<Place> result = jdbcTemplate.query(query_str, (rs, rowNum)->new Place(rs));
		return result;
	}

	public List<Place> getPlacefromLocation(String location){
		String query_str = String.format("%s%s%s%s%s%s%s",
				"SELECT places.pid, place_name, phone_num, website, address, city, state, country, postal_code, longitude, latitude\n"+
				"FROM travelling.places\n"+
				"WHERE places.city = \"",location,"\" OR places.state = \"",location,"\" OR places.country = \"",location,"\";");
		List<Place> result = jdbcTemplate.query(query_str, (rs, rowNum)->new Place(rs));
		return result;
	}

	public List<Place> getPlacefromTagAndLocation(String tag, String location){
		String query_str = String.format("%s%s%s%s%s%s%s%s%s",
				"SELECT places.pid, place_name, phone_num, website, address, city, state, country, postal_code, longitude, latitude\n"+
                "FROM travelling.places, travelling.placetag\n"+
				"WHERE places.pid = placetag.pid AND placetag.tid = (SELECT tid FROM travelling.tags WHERE tag=\"",tag,"\")"+
                        "AND (places.city = \"",location,"\" OR places.state = \"",location,"\" OR places.country = \"",location,"\");");
		List<Place> result = jdbcTemplate.query(query_str, (rs, rowNum)->new Place(rs));
		return result;
	}

	public GooglePlace UpdateFirstPage(String type, double lat, double lng){
		String URL = String.format("https://maps.googleapis.com/maps/api/place/textsearch/json?language=en&query=%s&location=%f, %f&radius=50000&key=%s",type,lat,lng, api_key);
		return this.RequestGooglePlaceSearch(URL);
	}

	public GooglePlace UpdateNextPage(String token){
		String URL = String.format("https://maps.googleapis.com/maps/api/place/textsearch/json?language=en&pagetoken=%s&key=%s",token,api_key);
		return this.RequestGooglePlaceSearch(URL);
	}

	private GooglePlace RequestGooglePlaceSearch(String URL){
		RestTemplate restTemplate = new RestTemplate();
		String json= restTemplate.getForObject(URL, String.class);
		GooglePlace googlePlace = gson.fromJson(json, GooglePlace.class);
		return googlePlace;
	}

	public String UpdateDatabase(String query, double lat, double lng){
		List<Results> place_results = new ArrayList<>();
		GooglePlace googlePlace;
		googlePlace = UpdateFirstPage(query, lat, lng);
		String status = googlePlace.getStatus();
		while(status.equals("OK")){
			place_results.addAll(googlePlace.getResults());
			String token = googlePlace.getNext_page_token();

			if(token == null || token.isEmpty())
				break;
			else{
				googlePlace = UpdateNextPage(token);
				status = googlePlace.getStatus();;
			}
		}

		// for each place save it to database and update tag table and placetag relation table
		for(Results results:place_results){
			Place place = UpdatePlaceTable(results);
			if(place != null)
				UpdatePlaceTag(results, place);
		}

		return status;
	}

	public boolean UpdateDatabaseAll(List<String> query_list, double lat, double lng){
		boolean error_occurs = false;
		for(String query:query_list){
			String status = UpdateDatabase(query, lat, lng);
			if(!status.equals("OK"))
				error_occurs = true;
		}
		return error_occurs;
	}

	public int findPidByFormattedAddress(Place place){
		String query_str = String.format("SELECT pid FROM places\n" +
				"WHERE address=\"%s\" AND city=\"%s\" AND state=\"%s\" AND zip_code=\"%s\" AND country=\"%s\"",
				place.getAddress(), place.getCity(), place.getState(), place.getZip_code(), place.getCountry());
		List<Integer> results = jdbcTemplate.query(query_str, (rs, rowNum)->rs.getInt("pid"));
		if(results.isEmpty())
			return -1;
		else
			return results.get(0);
	}

	public int findTidByTag(String tag){
		String query_str = String.format("SELECT tid FROM tags WHERE tag=\"%s\"",tag);
		List<Integer> results = jdbcTemplate.query(query_str, (rs, rowNum)->rs.getInt("tid"));
		if(results.isEmpty())
			return -1;
		else
			return results.get(0);

	}

	private Place UpdatePlaceTable(Results results){
		if(results == null)
			return null;
		String formatted_address =results.getFormatted_address();
		if(formatted_address == null || formatted_address.isEmpty())
			return null;

		Place place = new Place(results);
		int pid = findPidByFormattedAddress(place);
		if(pid != -1)
			place.setPid(pid);
		placeRepository.save(place);
		return place;
	}

	private void UpdatePlaceTag(Results results, Place place){
		List<String> tags = results.getTypes();
		tags.add(place.getCity());
		tags.add(place.getState());
		tags.add(place.getCountry());
		tags.add(place.getZip_code());
		for(String tag_str:tags){
			int tid = findTidByTag(tag_str);
			if(tid == -1){
				Tag tag = new Tag();
				tag.setTag(tag_str);
				tagRepository.save(tag);
				tid = tag.getTid();
			}

			PlaceTag placeTag= new PlaceTag(place.getPid(), tid);
			placeTagRepository.save(placeTag);
		}
	}

}
