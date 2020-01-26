package com.example.nerdeyesemproje;

import com.example.nerdeyesemproje.Restaurant.Example;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {
    //use key to use zomato api
    @Headers({
            "Accept: application/json","user-key: 64ec6daf406784ba88de56b7a6e032e9"
    })//take restaurant information from zomato api with location parameters
    @GET("search")
    Observable<Example> getRestaurantsBySearch(@Query("lat") Double latitude,
                                               @Query("lon") Double longtitude,
                                               @Query("sort") String string,
                                               @Query("count") int count);



}
