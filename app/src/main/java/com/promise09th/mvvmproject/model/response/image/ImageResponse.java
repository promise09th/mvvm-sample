package com.promise09th.mvvmproject.model.response.image;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResponse {

    @SerializedName("documents")
    List<ImageDocument> documents;

    public List<ImageDocument> getDocuments() {
        return this.documents;
    }
}
