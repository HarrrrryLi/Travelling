package com.Travelling.Repositories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.Travelling.Repositories.Entities.PlaceTag;
import com.Travelling.Repositories.Entities.Tag;
import com.Travelling.Repositories.Entities.User;
import com.Travelling.Repositories.GooglePlace.*;
import com.Travelling.Repositories.Entities.Place;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
    private UserRepository userRepository;

//	private Logger logger = LoggerFactory.getLogger(DBRepository.class);
	private final String api_key = "api-key";

	//use requests_cnt to make Google Server End cachless to fix INVALID_RESPONSE bug
	private static int requests_cnt = 0;
	
	public List<String> getaddress(int pid) {
		String query_str = String.format("SELECT address FROM places WHERE pid=%d", pid);
		List<String> address = jdbcTemplate.query(query_str, (rs, rowNum)->rs.getString("address"));
		return address;				
	}
	
	public List<Place> getPlacefromTid(int tid){
		String query_str = String.format("%s%d",
				"SELECT places\n"+
				"FROM travelling.places, travelling.placetag\n"+
				"WHERE places.pid = placetag.pid AND placetag.tid=",tid);
		List<Place> result = jdbcTemplate.query(query_str, (rs, rowNum)->new Place(rs));
		return result;
	}

	public List<Place> getPlacefromLocation(String location){
		String query_str = String.format("%s%s%s%s%s%s%s%s%s%s%s",
				"SELECT *\n"+
				"FROM travelling.places\n"+
				"WHERE city = \"",location,"\" OR state = \"",location,"\" OR country = \"",location,"\" OR address = \"",location,
				"\" OR zip_code=\"", location, "\";");
		List<Place> result = jdbcTemplate.query(query_str, (rs, rowNum)->new Place(rs));
		return result;
	}

	public List<Place> getPlacefromTagAndLocation(int tid, String location){
		String query_str = String.format("%s%d%s%s%s%s%s%s%s%s%s%s%s",
				"SELECT places.*\n"+
                "FROM travelling.places, travelling.placetag\n"+
				"WHERE places.pid = placetag.pid AND placetag.tid = ",tid,
				" AND (city = \"",location,"\" OR state = \"",location,"\" OR country = \"",location,"\" OR address = \"",location,
				"\" OR zip_code=\"", location, "\");");
		List<Place> result = jdbcTemplate.query(query_str, (rs, rowNum)->new Place(rs));
		return result;
	}

	//Request Google Place Part
	public GooglePlace GetFirstPage(String type, double lat, double lng){
		String URL = String.format("https://maps.googleapis.com/maps/api/place/textsearch/json?language=en&query=%s&location=%f,%f&radius=50000&requests_cnt=%d&key=%s",type,lat,lng,requests_cnt, api_key);
		return this.RequestGooglePlaceSearch(URL, GooglePlace.class);
	}

	public GooglePlace GetNextPage(String token){
		String URL = String.format("https://maps.googleapis.com/maps/api/place/textsearch/json?language=en&pagetoken=%s&requests_cnt=%d&key=%s",token, requests_cnt,api_key);
		return this.RequestGooglePlaceSearch(URL, GooglePlace.class);
	}

	private <T> T RequestGooglePlaceSearch(String URL,Type type){
		RestTemplate restTemplate = new RestTemplate();
		String json= restTemplate.getForObject(URL, String.class);
		T googlePlace = gson.fromJson(json,type);
		try{
			TimeUnit.SECONDS.sleep(1);
		}catch (InterruptedException e){

		}
		requests_cnt ++;
		return googlePlace;
	}

	public String UpdateDatabase(String query, double lat, double lng){
		List<Results> place_results = new ArrayList<>();
		GooglePlace googlePlace = GetFirstPage(query, lat, lng);
		String status = googlePlace.getStatus();
		String token = "";
		int invalid_requests_num = 0;
		while(true){
			if(status.equals("OK")){
				invalid_requests_num = 0;
				place_results.addAll(googlePlace.getResults());
				token = googlePlace.getNext_page_token();

				if(token == null || token.isEmpty())
					break;
				else{
					googlePlace = GetNextPage(token);
					status = googlePlace.getStatus();
				}
			}
			else if(status.equals("INVALID_REQUEST") && !token.isEmpty() && invalid_requests_num <= 1000){
				invalid_requests_num ++;
				googlePlace = GetNextPage(token);
				status = googlePlace.getStatus();
			}
			else
				break;
		}

		// for each place save it to database and update tag table and placetag relation table
		for(Results results:place_results){
			Place place = UpdatePlaceTable(results);
			if(place != null)
				UpdatePlaceTag(place,query);
		}

		return status;
	}

	public boolean UpdateDatabaseAll(List<String> query_list, double lat, double lng){
		boolean error_occurs = false;
		for(String query:query_list){
			String status = UpdateDatabase(query, lat, lng);
			error_occurs |= !status.equals("OK");
		}
		return error_occurs;
	}

	public Details GetPlaceDetails(String id){
		String URL = String.format("https://maps.googleapis.com/maps/api/place/details/json?language=en&placeid=%s&fields=formatted_phone_number,website,reviews&&key=%s",id, api_key);
		return this.RequestGooglePlaceSearch(URL, Details.class);
	}

	// Update/Query Table parts
	public int findPidByFormattedAddress(Place place){
		String query_str = String.format("SELECT pid FROM places\n" +
				"WHERE place_name=\"%s\" AND address=\"%s\" AND city=\"%s\" AND state=\"%s\" AND zip_code=\"%s\" AND country=\"%s\"",
				place.getName(), place.getAddress(), place.getCity(), place.getState(), place.getZip_code(), place.getCountry());
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

		// request place details
		String place_id = results.getPlace_id();
		Details details = GetPlaceDetails(place_id);
		String status = details.getStatus();

		Place place = new Place(results);
		if(status.equals("OK")){
			DetailResult detailResult = details.getDetailResult();
			place.setPhone_num(detailResult.getPhone_num());
			place.setWebsite(detailResult.getWebsite());
			if(detailResult.getReviews() != null)
				place.setRating_nums(detailResult.getReviews().size());
		}

		List<Photos> photos = results.getPhotos();
		if(photos != null)
			place.setImg_path(photos.get(0).getPhoto_reference());

		int pid = findPidByFormattedAddress(place);
		if(pid != -1)
			place.setPid(pid);


		placeRepository.save(place);
		return place;


	}

	private void UpdatePlaceTag(Place place, String tag_str){

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

	public void UpdateAdminUser(){
        if(userRepository.existsByUsername("hhaarryy") == 0){
            User admin = new User();
            admin.setPassword(new BCryptPasswordEncoder().encode("123456"));
            admin.setEmail("lfrharry@gmail.com");
            admin.setUsername("hhaarryy");
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }

    public List<Tag> FindAllTags(){
		return tagRepository.findAll();
	}

	public long CountUsers(){
		return userRepository.count();
	}

	public long CountPlaces(String type){
		String query_str = String.format("%s%s%s",
				"SELECT COUNT(places.pid) AS numbers\n"+
						"FROM travelling.places, travelling.placetag, travelling.tags\n"+
						"WHERE places.pid = placetag.pid AND placetag.tid= tags.tid AND tags.tag = \"",type ,"\"");
		List<Long> result = jdbcTemplate.query(query_str, (rs, rowNum) -> rs.getLong("numbers"));
		return result.get(0);
	}

	public long CountDestinations(){
		String query_str = 	"SELECT COUNT(DISTINCT city) AS numbers FROM travelling.places";

		List<Long> result = jdbcTemplate.query(query_str, (rs, rowNum) -> rs.getLong("numbers"));
		return result.get(0);
	}

	public List<Place> getPlacefromTag(String tag){
		String query_str = String.format("%s%s%s",
				"SELECT places.*\n"+
						"FROM travelling.places, travelling.placetag, travelling.tags\n"+
						"WHERE places.pid = placetag.pid AND placetag.tid= tags.tid AND tags.tag = \"",tag ,"\"");
		List<Place> result = jdbcTemplate.query(query_str, (rs, rowNum)->new Place(rs));
		return result;
	}

}
