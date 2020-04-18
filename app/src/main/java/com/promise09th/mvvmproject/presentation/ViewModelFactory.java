package com.promise09th.mvvmproject.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRepository;
import com.promise09th.mvvmproject.presentation.main.ThumbnailViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final ThumbnailRepository mThumbnailRepository;

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            Injection.provideThumbnailRepository(application.getApplicationContext())
                    );
                }
            }
        }
        return INSTANCE;
    }

    private ViewModelFactory(ThumbnailRepository thumbnailRepository) {
        mThumbnailRepository = thumbnailRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ThumbnailViewModel.class)) {
            //noinspection unchecked
            return (T) new ThumbnailViewModel(mThumbnailRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}