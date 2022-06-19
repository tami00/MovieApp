package com.example.cinemaapp.API;

import com.example.cinemaapp.models.Movies;
import com.example.cinemaapp.models.VideoPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("now_playing?")
    Call<Movies> getActionMovies(@Query("api_key") String apiKey,
                                 @Query("language") String language,
                                 @Query("page") int page,
                                 @Query("with_genres") int genres);

    @GET("now_playing")
    Call<Movies> getAllMovies(@Query("api_key") String apiKey,
                                 @Query("language") String language,
                                 @Query("page") int page);
}
//https://api.themoviedb.org/3/movie/now_playing?api_key=f134dfeac1ebb17feefa58d7f94e94cd&language=en-US&page=1&with_genres=28
