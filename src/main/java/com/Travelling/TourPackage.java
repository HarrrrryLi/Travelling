package com.Travelling;


public class TourPackage extends Object {
    private String city, country;
    private float rating,price;
    private int rating_nums;

    public TourPackage(){
    }

    public TourPackage(String city, String country, float rating, float price, int rating_nums){
        this.city = city;
        this.country = country;
        this.price = price;
        this.rating = rating;
        this.rating_nums = rating_nums;
    }
    public TourPackage(String s){
        String[] args = s.split(";");
        city = args[0].split(":")[1];
        country = args[1].split(":")[1];
        price = Float.parseFloat(args[2].split(":")[1]);
        rating = Float.parseFloat(args[3].split(":")[1]);
        rating_nums = Integer.parseInt(args[4].split(":")[1]);
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRating_nums() {
        return rating_nums;
    }

    public void setRating_nums(int rating_nums) {
        this.rating_nums = rating_nums;
    }

    public String toString(){
        return String.format("city:%s;country:%s;price:%f;rating:%f;rating_nums:%d",city,country,price,rating,rating_nums);
    }

}
