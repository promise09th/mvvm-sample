package com.promise09th.mvvmproject.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Optional;

public class ImageThumbnail extends Thumbnail {
    @SerializedName("collection")
    private String mCollection;

    @SerializedName("datetime")
    private String mDateTime;

    @SerializedName("doc_url")
    private String mDocUrl;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("thumbnail_url")
    private String mThumbnailUrl;

    @SerializedName("height")
    private int mHeight;

    @SerializedName("mWidth")
    private int mWidth;

    public ImageThumbnail() {

    }

    @Override
    public String getName() {
        return Optional.ofNullable(mCollection)
                .filter(t -> !TextUtils.isEmpty(t))
                .map(t -> "Image : " + t)
                .orElse("Image");
    }

    @Override
    public String getDateTime() {
        return mDateTime;
    }

    @Override
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public String getCollection() {
        return mCollection;
    }

    public String getDocUrl() {
        return mDocUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }
}
