package com.example.cinemaapp.API;

import com.example.cinemaapp.models.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieSearchAPI {

    @GET("movie?/")
    Call<Movies> getSearch(@Query("api_key") String api_key,
                           @Query("language") String language,
                           @Query("query") String query);
}
