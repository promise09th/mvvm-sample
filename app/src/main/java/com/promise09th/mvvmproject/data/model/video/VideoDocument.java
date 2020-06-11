package com.promise09th.mvvmproject.data.model.video;

import com.google.gson.annotations.SerializedName;

public class VideoDocument {

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

    public VideoDocument() {

    }

    public String getTitle() {
        return mTitle;
    }

    public String getDocUrl() {
        return mDocUrl;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public String getPlayTime() {
        return mPlayTime;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
