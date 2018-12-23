package com.Travelling;

import java.util.List;

import com.Travelling.GooglePlace.GooglePlace;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DBRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Gson gson;

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
				"WHERE places.pid = placetag.pid and placetag.tid = (SELECT tid FROM travelling.tags WHERE tag=\"",tag,"\");");
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
		String URL = String.format("https://maps.googleapis.com/maps/api/place/textsearch/json?type=%s&location=%d, %d&radius=50000&key=%s",type,lat,lng, api_key);
		return this.RequestGooglePlaceSearch(URL);
	}

	public GooglePlace UpdateNextPage(GooglePlace prePlace){
		String URL = String.format("https://maps.googleapis.com/maps/api/place/textsearch/json?pagetoken=%s&key=%s",prePlace.getNext_page_token(),api_key);
		return this.RequestGooglePlaceSearch(URL);
	}


	private GooglePlace RequestGooglePlaceSearch(String URL){
		RestTemplate restTemplate = new RestTemplate();
		String json= restTemplate.getForObject(URL, String.class);
		GooglePlace googlePlace = gson.fromJson(json, GooglePlace.class);
		return googlePlace;
	}
	

}
