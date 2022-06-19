package com.example.cinemaapp.models;

public class Snippet
{
    private String playlistId;

    private ResourceId resourceId;

    private String publishedAt;

    private String description;

    private String position;

    private String title;

    private Thumbnails thumbnails;

    private String channelId;

    private String videoOwnerChannelId;

    private String channelTitle;

    private String videoOwnerChannelTitle;

    public String getPlaylistId ()
    {
        return playlistId;
    }

    public void setPlaylistId (String playlistId)
    {
        this.playlistId = playlistId;
    }

    public ResourceId getResourceId ()
    {
        return resourceId;
    }

    public void setResourceId (ResourceId resourceId)
    {
        this.resourceId = resourceId;
    }

    public String getPublishedAt ()
    {
        return publishedAt;
    }

    public void setPublishedAt (String publishedAt)
    {
        this.publishedAt = publishedAt;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getPosition ()
    {
        return position;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Thumbnails getThumbnails ()
    {
        return thumbnails;
    }

    public void setThumbnails (Thumbnails thumbnails)
    {
        this.thumbnails = thumbnails;
    }

    public String getChannelId ()
    {
        return channelId;
    }

    public void setChannelId (String channelId)
    {
        this.channelId = channelId;
    }

    public String getVideoOwnerChannelId ()
    {
        return videoOwnerChannelId;
    }

    public void setVideoOwnerChannelId (String videoOwnerChannelId)
    {
        this.videoOwnerChannelId = videoOwnerChannelId;
    }

    public String getChannelTitle ()
    {
        return channelTitle;
    }

    public void setChannelTitle (String channelTitle)
    {
        this.channelTitle = channelTitle;
    }

    public String getVideoOwnerChannelTitle ()
    {
        return videoOwnerChannelTitle;
    }

    public void setVideoOwnerChannelTitle (String videoOwnerChannelTitle)
    {
        this.videoOwnerChannelTitle = videoOwnerChannelTitle;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [playlistId = "+playlistId+", resourceId = "+resourceId+", publishedAt = "+publishedAt+", description = "+description+", position = "+position+", title = "+title+", thumbnails = "+thumbnails+", channelId = "+channelId+", videoOwnerChannelId = "+videoOwnerChannelId+", channelTitle = "+channelTitle+", videoOwnerChannelTitle = "+videoOwnerChannelTitle+"]";
    }
}

