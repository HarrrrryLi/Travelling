/**
 * This class is to show suggestion destination class
 */

package com.Travelling;

import org.springframework.stereotype.Component;

@Component
public class Destination{
    private String city;
    private String country;
    private String state;
    private float rate;
    private int rating_nums;
    private String img_path;

    public Destination(){
    }

    public Destination(Destination destination){
        this.city = destination.city;
        this.country = destination.country;
        this.rate = destination.rate;
        this.rating_nums = destination.rating_nums;
        this.img_path = destination.img_path;
        this.state = destination.state;
    }

    public Destination(String city, String state, String country, float rate, int rating_nums, String path){
        this.city = city;
        this.country = country;
        this.rate = rate;
        this.rating_nums = rating_nums;
        this.img_path = path;
        this.state = state;
    }
    public Destination(String s){
        String[] args = s.split(";");
        city = args[0].split(":")[1];
        country = args[1].split(":")[1];
        rate = Float.parseFloat(args[2].split(":")[1]);
        rating_nums = Integer.parseInt(args[3].split(":")[1]);
        img_path = args[4].split(":")[1];
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rating) {
        this.rate = rating;
    }

    public int getRating_nums() {
        return rating_nums;
    }

    public void setRating_nums(int rating_nums) {
        this.rating_nums = rating_nums;
    }

    public String getImg_path() {
        return img_path;
    }

    public String getName(){
        return city;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getState(){
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String toString(){
        return String.format("city:%s;country:%s;rate:%f;rating_nums:%d;img_path:%s",city,country,rate,rating_nums,img_path);
    }
}
