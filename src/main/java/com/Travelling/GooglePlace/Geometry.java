package com.Travelling.GooglePlace;

import com.google.gson.annotations.SerializedName;

public class Geometry {
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @SerializedName("location")
    private Location location;


}
