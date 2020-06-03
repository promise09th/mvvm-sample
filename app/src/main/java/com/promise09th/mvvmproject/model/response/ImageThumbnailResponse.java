package com.promise09th.mvvmproject.model.response;

import com.google.gson.annotations.SerializedName;
import com.promise09th.mvvmproject.model.thumbnail.ImageThumbnail;

import java.util.List;

public class ImageThumbnailResponse {

    @SerializedName("documents")
    List<ImageThumbnail> documents;

    public List<ImageThumbnail> getDocuments() {
        return this.documents;
    }
}
