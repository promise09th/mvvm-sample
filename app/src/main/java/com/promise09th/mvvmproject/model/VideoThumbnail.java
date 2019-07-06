package com.promise09th.mvvmproject.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Optional;

public class VideoThumbnail extends Thumbnail {
    @SerializedName("title")
    private String mTitle;

    @SerializedName("url")
    private String mDocUrl;

    @SerializedName("datetime")
    private String mDateTime;

    @SerializedName("play_time")
    private String mPlayTime;

    @SerializedName("thumbnail")
    private String mThumbnailUrl;

    @SerializedName("author")
    private String mAuthor;

    public VideoThumbnail() {

    }

    @Override
    public String getName() {
        return Optional.ofNullable(mTitle)
                .filter(t -> !TextUtils.isEmpty(t))
                .map(t -> "Video : " + mTitle)
                .orElse("Video");
    }

    @Override
    public String getDateTime() {
        return mDateTime;
    }

    @Override
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDocUrl() {
        return mDocUrl;
    }

    public String getPlayTime() {
        return mPlayTime;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
