package com.example.cinemaapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieSearchRetrofit {
    private static MovieSearchRetrofit mInstance;
    private static final String BASE_URL = "https://api.themoviedb.org/3/search/";
    private static Retrofit retrofit;

    private MovieSearchRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MovieSearchRetrofit getmInstance() {
        if (mInstance == null) {
            mInstance = new MovieSearchRetrofit();
        }
        return mInstance;
    }

    public MovieSearchAPI getAPI(){
        return retrofit.create(MovieSearchAPI.class);
    }
}
