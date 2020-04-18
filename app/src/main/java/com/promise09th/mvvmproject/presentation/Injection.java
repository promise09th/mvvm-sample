package com.promise09th.mvvmproject.presentation;

import android.content.Context;

import androidx.annotation.NonNull;

import com.promise09th.mvvmproject.data.thumbnail.ThumbnailLocalDataSource;
import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRemoteDataSource;
import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRepository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class Injection {

    public static ThumbnailRepository provideThumbnailRepository(@NonNull Context context) {
        checkNotNull(context);
        return ThumbnailRepository.getInstance(
                ThumbnailLocalDataSource.getInstance(),
                ThumbnailRemoteDataSource.getInstance());
    }
}

