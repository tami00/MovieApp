package com.example.cinemaapp.API;

import com.example.cinemaapp.models.ReviewPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReviewAPI {

    @GET("search.json?")
    Call<ReviewPOJO> getReviews(@Query("api-key")String apiKey);
}
