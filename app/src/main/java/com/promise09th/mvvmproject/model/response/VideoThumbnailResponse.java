package com.promise09th.mvvmproject.model.response;

import com.google.gson.annotations.SerializedName;
import com.promise09th.mvvmproject.model.thumbnail.VideoThumbnail;

import java.util.List;

public class VideoThumbnailResponse {

    @SerializedName("documents")
    List<VideoThumbnail> documents;

    public List<VideoThumbnail> getDocuments() {
        return this.documents;
    }
}
