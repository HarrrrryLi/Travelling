package com.Travelling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	

}
