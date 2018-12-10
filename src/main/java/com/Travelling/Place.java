package com.Travelling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.hibernate.annotations.Tables;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="travelling.places")
public class Place extends Object {
	
	@Id
    @Column(name="pid")
    private int pid;
	@Column(name="place_name")
	private String name;
	@Column(name="phone_num")
	private String phone_num;
	@Column(name="website")
	private String website;

	@Column(name="address")
	private String address;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="country")
	private String country;
	@Column(name="postal_code")
	private String zip_code;
	@Column(name="longitude")
	private double longitude;
	@Column(name="latitude")
	private double latitude;
	
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

	public Place(String str){
	    String[] attributes = str.split(";");
	    setPid(Integer.parseInt(attributes[0].split(":")[1]));
	    setName(checkString(attributes[1].split(":")[1]));
	    setPhone_num(checkString(attributes[2].split(":")[1]));
        setWebsite(checkString(attributes[3].split(":")[1]));
        setAddress(checkString(attributes[4].split(":")[1]));
        setCity(checkString(attributes[5].split(":")[1]));
        setState(checkString(attributes[6].split(":")[1]));
        setCountry(checkString(attributes[7].split(":")[1]));
        setZip_code(checkString(attributes[8].split(":")[1]));
        setLongitude(Double.parseDouble(attributes[9].split(":")[1]));
        setLatitude(Double.parseDouble(attributes[10].split(":")[1]));
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
		return String.format("pid:%d;name:%s;phone:%s;website:%s;address:%s;city:%s;state:%s;country:%s;zip_code:%s;longitude:%f;latitude:%f",
				pid,name,phone_num,website,address,city,state,country,zip_code,longitude,latitude);
	}

	private String checkString(String str){
		if(str.equals("null"))
			return "";
		else
			return str;
	}
}
