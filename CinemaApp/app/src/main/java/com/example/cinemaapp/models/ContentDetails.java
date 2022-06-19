package com.example.cinemaapp.models;

public class ContentDetails {
    private String videoPublishedAt;

    private String videoId;

    public String getVideoPublishedAt() {
        return videoPublishedAt;
    }

    public void setVideoPublishedAt(String videoPublishedAt) {
        this.videoPublishedAt = videoPublishedAt;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "ClassPojo [videoPublishedAt = " + videoPublishedAt + ", videoId = " + videoId + "]";
    }
}