package com.Travelling;

import org.springframework.stereotype.Component;

@Component
public class ServicePlace extends Place implements DisplayPlace {
    private float rate, price;
    private int rating_nums;
    private String img_path;

    public ServicePlace(){};

    public ServicePlace(Place place, float rate, float price, int rating_nums, String img_path){
        this.price = price;
        this.rate = rate;
        this.rating_nums = rating_nums;
        this.img_path = img_path;

        this.pid = place.pid;
        this.name = place.name;
        this.address = place.address;
        this.city = place.city;
        this.state = place.state;
        this.country = place.country;
        this.longitude = place.longitude;
        this.latitude = place.latitude;
        this.phone_num = place.phone_num;
        this.website = place.website;
        this.zip_code = place.zip_code;
    }

    public ServicePlace(String str){
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

        price = Float.parseFloat(args[11].split(":")[1]);
        rate = Float.parseFloat(args[12].split(":")[1]);
        rating_nums = Integer.parseInt(args[13].split((":"))[1]);

    }

    // ONLY FOR TEST
    public ServicePlace(String name, String city, String country, float price, float rate, int rating_nums){
        this.name = name;
        this.city = city;
        this.country = country;
        this.price = price;
        this.rate = rate;
        this.rating_nums = rating_nums;
    }

    public ServicePlace(String name, String city, String country, float price, float rate, int rating_nums, String path){
        this.name = name;
        this.city = city;
        this.country = country;
        this.price = price;
        this.rate = rate;
        this.rating_nums = rating_nums;
        this.img_path = path;
    }

    @Override
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int getRating_nums() {
        return rating_nums;
    }

    public void setRating_nums(int rating_nums) {
        this.rating_nums = rating_nums;
    }

    @Override
    public String getImg_path(){
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    @Override
    public String toString() {
        return String.format("pid:%d;name:%s;phone:%s;website:%s;address:%s;city:%s;state:%s;country:%s;zip_code:%s;longitude:%f;latitude:%f;price:%f;rate:%f;rating_nums:%f;img_path:%s",
                pid, name, phone_num, website, address, city, state, country, zip_code, longitude, latitude, price, rate, rating_nums, img_path);
    }
}
