package com.fengru.Travelling;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
public class Place {
	
	private int pid;
	private String name, phone_num, website;
	private String address, city, state, country, zip_code;
	private double longitude, latitude;
	
	public Place() {
		
	}
	public Place(int id, String name, String phone, String website, String address, String city, String state, String country, String zip, double longitude, double latitude) {
		setPid(id);
		setName(name);
		setPhone_num(phone);
		setWebsite(website);
		setAddress(address);
		setCity(city);
		setState(state);
		setCountry(country);
		setZip_code(zip);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	
	public Place(ResultSet rs) throws SQLException{
		
		setPid(rs.getInt("pid"));
		setLongitude(rs.getDouble("longitude"));
		setLatitude(rs.getDouble("latitude"));
		

		try {
			setName(rs.getString("place_name"));
		} catch (SQLException e) {
			setName("");
		}
		
		try {
			setPhone_num(rs.getString("phone_num"));
		}
		catch (SQLException e) {
			setPhone_num("");
		}
		
		try {
			setWebsite(rs.getString("website"));
		}
		catch (SQLException e) {
			setWebsite("");
		}
		
		try {
			setAddress(rs.getString("address"));
		} catch (SQLException e) {
			setAddress("");
		}
		
		try {
			setCity(rs.getString("city"));
		} catch (SQLException e) {
			setCity("");
		}
		
		try {
			setState(rs.getString("state"));
		} catch (SQLException e) {
			setState("");
		}
		
		try {
			setCountry(rs.getString("country"));
		} catch (SQLException e) {
			setCountry("");
		}
		
		try {
			setZip_code(rs.getString("postal_code"));
		} catch (SQLException e) {
			setZip_code("");
		}
		
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	@Override
	public String toString() {
		return String.format("pid:%d\nname:%s\nphone:%s\nwebsite:%s\naddress:%s\ncity:%s\nstate:%s\ncountry:%s\nzip_code:%s\nlongitude:%f\nlatitude:%f", 
				pid,name,phone_num,website,address,city,state,country,zip_code,longitude,latitude);
	}



}
