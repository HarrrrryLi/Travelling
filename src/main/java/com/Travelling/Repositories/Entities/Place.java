package com.Travelling.Repositories.Entities;

import com.Travelling.Repositories.GooglePlace.Location;
import com.Travelling.Repositories.GooglePlace.Results;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.*;

@Entity
@Table(name = "places")
public class Place{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    protected int pid;
	@Column(name = "place_name")
	protected String name;
	@Column(name = "phone_num")
	protected String phone_num;
	@Column(name = "website")
	protected String website;

	@Column(name = "address")
	protected String address;
	@Column(name = "city")
	protected String city;
	@Column(name = "state")
	protected String state;
	@Column(name = "country")
	protected String country;
	@Column(name = "zip_code")
	protected String zip_code;
	@Column(name = "longitude")
	protected double longitude;
	@Column(name = "latitude")
	protected double latitude;
	@Column(name = "rate")
	protected float rate;
	@Column(name = "rating_nums")
	protected int rating_nums;
	@Column(name = "price")
	protected float price;
	@Column(name = "img_path")
	protected String img_path;
	@Column(name = "description")
    protected  String description;

	public Place() {
		
	}

	/**TEST ONLY**/
	public Place(int id, String name, String phone, String website, String address, String city, String state, String country, String zip, double longitude, double latitude, float rate, int rating_nums, float price, String img_path, String description) {
		this.pid = id;
		this.name = name;
		this.phone_num = phone;
		this.website = website;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip_code = zip;
		this.longitude = longitude;
		this.latitude = latitude;
		this.rate = rate;
		this.rating_nums = rating_nums;
		this.price = price;
		this.img_path = img_path;
		this.description = description;
	}

    public Place(int id, String name, String phone, String website, String address, String city, String state, String country, String zip, double longitude, double latitude) {
        this.pid = id;
        this.name = name;
        this.phone_num = phone;
        this.website = website;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip_code = zip;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Place(int id, String name, String city, String state, String country, float price, float rate, int rating_nums, String img_path) {
        this.pid = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.rate = rate;
        this.rating_nums = rating_nums;
        this.price = price;
        this.img_path = img_path;
    }
    /**TEST ONLY**/

    public Place(Results results){
        name = results.getName();
        String formatted_address = results.getFormatted_address();
        String[] address_parts = formatted_address.split(",");
        if(address_parts.length == 5) {
            address = String.format("%s, %s", address_parts[0], address_parts[1]);
            city = address_parts[2].trim();
            String[] state_zip = address_parts[3].trim().split(" ");
            state = state_zip[0].trim();
            if(state_zip.length > 1)
                zip_code = state_zip[1].trim();
            country = address_parts[4].trim();

        }
        else if(address_parts.length == 4){
            address = address_parts[0].trim();
            city = address_parts[1].trim();
            String[] state_zip = address_parts[2].trim().split(" ");
            state = state_zip[0].trim();
            if(state_zip.length > 1)
                zip_code = state_zip[1].trim();
            country = address_parts[3].trim();
        }
        else{
            city = address_parts[0].trim();
            String[] state_zip = address_parts[1].trim().split(" ");
            state = state_zip[0].trim();
            if(state_zip.length > 1)
                zip_code = state_zip[1].trim();
            country = address_parts[2].trim();
        }


        rate = (float)results.getRating();
        price = results.getPrice_level();
        if(rate != 0)
            rating_nums = 100;
        else
            rating_nums = 0;

        Location location = results.getGeometry().getLocation();
        latitude = location.getLat();
        longitude = location.getLng();
    }

	public Place(String str){
		String[] args = str.split(";");
		pid = Integer.parseInt(args[0].split(":")[1]);
		name = checkString(args[1].split(":")[1]);
		phone_num = checkString(args[2].split(":")[1]);
		website = checkString(args[3].split(":")[1]);
		address = checkString(args[4].split(":")[1]);
		city = checkString(args[5].split(":")[1]);
		state = checkString(args[6].split(":")[1]);
		country = checkString(args[7].split(":")[1]);
		zip_code = checkString(args[8].split(":")[1]);
		longitude = Double.parseDouble(args[9].split(":")[1]);
		latitude = Double.parseDouble(args[10].split(":")[1]);
        rate = Float.parseFloat(args[11].split(":")[1]);
        rating_nums = Integer.parseInt(args[12].split(":")[1]);
        price = Float.parseFloat(args[13].split(":")[1]);
        img_path = args[14].split(":")[1];
        description = args[15].split(":")[1];
    }

	public Place(ResultSet rs) throws SQLException {

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
        } catch (SQLException e) {
            setPhone_num("");
        }

        try {
            setWebsite(rs.getString("website"));
        } catch (SQLException e) {
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

        try {
            setRate(rs.getFloat("rate"));
        } catch (SQLException e){
            setRate(0);
        }

        try {
            setRating_nums(rs.getInt("rating_nums"));
        } catch (SQLException e){
            setRating_nums(0);
        }

        try{
            setPrice(rs.getFloat("price"));
        } catch (SQLException e){
            setPrice(-1);
        }

        try{
            setImg_path(rs.getString("img_path"));
        } catch (SQLException e){
            setImg_path("");
        }

        try {
            setDescription(rs.getString("description"));
        } catch (SQLException e){
            setDescription(" ");
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getRating_nums() {
        return rating_nums;
    }

    public void setRating_nums(int rating_nums) {
        this.rating_nums = rating_nums;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
		return String.format("pid:%d;name:%s;phone:%s;website:%s;address:%s;city:%s;state:%s;country:%s;zip_code:%s;longitude:%f;latitude:%f",
				pid,name,phone_num,website,address,city,state,country,zip_code,longitude,latitude);
	}

	protected String checkString(String str){
		if(str.equals("null"))
			return "";
		else
			return str;
	}

	public void update(Place place){
        this.pid = place.pid;
    }
}
