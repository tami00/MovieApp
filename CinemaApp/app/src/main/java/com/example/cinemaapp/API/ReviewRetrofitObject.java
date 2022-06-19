package com.example.cinemaapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewRetrofitObject {
    private static ReviewRetrofitObject mInstance;
    private static final String BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/";
    private static Retrofit retrofit;

    private ReviewRetrofitObject() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ReviewRetrofitObject getmInstance() {
        if (mInstance == null) {
            mInstance = new ReviewRetrofitObject();
        }
        return mInstance;
    }

    public ReviewAPI getAPI(){
        return retrofit.create(ReviewAPI.class);
    }
}
