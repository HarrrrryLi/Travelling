package com.Travelling;

import com.Travelling.Repositories.Entities.Place;

import java.util.Comparator;

public class PlaceRateComparator implements Comparator<Place> {
    @Override
    public int compare(Place a, Place b){
        if(a.getRate() > b.getRate())
            return 1;
        else if(a.getRate() < b.getRate())
            return -1;
        else{
            if(a.getRating_nums() > b.getRating_nums())
                return 1;
            else if(a.getRating_nums() < b.getRating_nums())
                return -1;
            else
                return 0;
        }
    }
}
