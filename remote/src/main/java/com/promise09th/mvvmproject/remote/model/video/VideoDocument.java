package com.promise09th.mvvmproject.remote.model.video;

import com.google.gson.annotations.SerializedName;

public class VideoDocument {

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String docUrl;

    @SerializedName("datetime")
    private String dateTime;

    @SerializedName("play_time")
    private String playTime;

    @SerializedName("thumbnail")
    private String thumbnailUrl;

    @SerializedName("author")
    private String author;

    public VideoDocument() {

    }

    public String getTitle() {
        return this.title;
    }

    public String getDocUrl() {
        return this.docUrl;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public String getPlayTime() {
        return this.playTime;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public String getAuthor() {
        return this.author;
    }
}
