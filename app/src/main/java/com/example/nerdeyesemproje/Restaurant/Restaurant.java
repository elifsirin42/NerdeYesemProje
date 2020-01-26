package com.example.nerdeyesemproje.Restaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    @SerializedName("restaurant")
    @Expose
    private Restaurant_ restaurant;

    public Restaurant_ getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant_ restaurant) {
        this.restaurant = restaurant;
    }

}
