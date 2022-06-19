package com.google.android.youtube.player;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YoutubePlayerSupportFragmentX;

public class VideoFragment extends YoutubePlayerSupportFragmentX implements YouTubePlayer.OnInitializedListener {

    private OnVideoPlayListener onVideoPlayListener;

    public interface OnVideoPlayListener {
        void onPlaying(String videoId);
    }

    String videoId;

    public VideoFragment() {

    }

    public void setVideoId(final String mVideoId) {
        videoId = mVideoId;
        initialize("youtubeAPI", this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(videoId !=null){
            if(b){
                youTubePlayer.play();
            }
            else {
                youTubePlayer.loadVideo(videoId);
            }

            if(onVideoPlayListener!= null){
                onVideoPlayListener.onPlaying(videoId);
            }
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if(youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(getActivity(), 1).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString("KEY_VIDEO_ID", videoId);
    }
}
