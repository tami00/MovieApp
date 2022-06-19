package com.example.cinemaapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRetrofitObject {
    private static MovieRetrofitObject mInstance;
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static Retrofit retrofit;

    private MovieRetrofitObject() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MovieRetrofitObject getmInstance() {
        if (mInstance == null) {
            mInstance = new MovieRetrofitObject();
        }
        return mInstance;
    }

    public MovieAPI getAPI(){
        return retrofit.create(MovieAPI.class);
    }
}
