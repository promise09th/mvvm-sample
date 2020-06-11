package com.promise09th.mvvmproject.data.model.image;

import com.google.gson.annotations.SerializedName;

public class ImageDocument {
    @SerializedName("collection")
    private String mCollection;

    @SerializedName("thumbnail_url")
    private String mThumbnailUrl;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("mWidth")
    private int mWidth;

    @SerializedName("height")
    private int mHeight;

    @SerializedName("display_sitename")
    private String mDisplaySiteName;

    @SerializedName("doc_url")
    private String mDocUrl;

    @SerializedName("datetime")
    private String mDateTime;

    public ImageDocument() {

    }

    public String getCollection() {
        return mCollection;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public String getDisplaySiteName() {
        return mDisplaySiteName;
    }

    public String getDocUrl() {
        return mDocUrl;
    }

    public String getDateTime() {
        return mDateTime;
    }
}
