package com.promise09th.mvvmproject.remote.model.video;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {

    @SerializedName("documents")
    List<VideoDocument> documents;

    public List<VideoDocument> getDocuments() {
        return this.documents;
    }
}
