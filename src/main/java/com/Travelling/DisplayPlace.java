package com.Travelling;

import org.springframework.stereotype.Component;

@Component
public interface DisplayPlace {
    public int getRating_nums();
    public float getRate();
    public String getImg_path();
    public String toString();
    public String getCity();
    public String getCountry();
    public String getName();
}
