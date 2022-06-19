package com.example.cinemaapp.models;

public class ResourceId {
    private String kind;

    private String videoId;

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    public String getVideoId ()
    {
        return videoId;
    }

    public void setVideoId (String videoId)
    {
        this.videoId = videoId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [kind = "+kind+", videoId = "+videoId+"]";
    }
}
