package com.promise09th.mvvmproject.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.presentation.main.ThumbnailViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ThumbnailRepository thumbnailRepository;

    @Inject
    public ViewModelFactory(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ThumbnailViewModel.class)) {
            //noinspection unchecked
            return (T) new ThumbnailViewModel(thumbnailRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}