/**
 *  This Interface is used for display all kinds of places
 **/
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
    public String getState();
    public int getPid();
    public String getDescription();
}
