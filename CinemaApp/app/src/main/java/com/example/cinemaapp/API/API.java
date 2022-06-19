package com.example.cinemaapp.API;

//import com.example.cinemaapp.models.ResponseVideos;
import com.example.cinemaapp.models.VideoPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("playlistItems?part=snippet%2CcontentDetails")
    Call<VideoPOJO> getAllVideos(@Query("maxResults") String maxResults,
                                     @Query("playlistId") String playlistId,
                                     @Query("key") String key);

}

//https://www.googleapis.com/youtube/v3/search?
// key=AIzaSyCkww9Jl7RO7Dwb8jDdROHYLu97JNIwFUY&
// channelId=UCT4ayiqWW9qOqOq_u8trQTg&
// part=snippet&
// order=date&
// maxresults=50&
// type=video