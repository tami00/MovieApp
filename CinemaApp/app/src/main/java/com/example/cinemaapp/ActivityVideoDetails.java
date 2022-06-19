package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.VideoFragment;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

public class ActivityVideoDetails extends AppCompatActivity {

    String videoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);

        getData();
        playVideo();
    }

    private void getData() {
        Intent intent = getIntent();

        videoId = intent.getStringExtra("videoId");
    }

    private void playVideo(){
        final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);

        if(result!= YouTubeInitializationResult.SUCCESS){
            result.getErrorDialog(this, 0).show();
            return;
        }

        final VideoFragment videoFragment = (VideoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fargment_youtube);
        videoFragment.setVideoId(videoId);
    }
}