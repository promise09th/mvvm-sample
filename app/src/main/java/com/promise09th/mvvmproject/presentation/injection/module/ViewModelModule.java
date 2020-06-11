package com.promise09th.mvvmproject.presentation.injection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.promise09th.mvvmproject.presentation.ViewModelFactory;
import com.promise09th.mvvmproject.presentation.injection.scope.ViewModelKey;
import com.promise09th.mvvmproject.presentation.main.ThumbnailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ThumbnailViewModel.class)
    protected abstract ViewModel thumbnailViewModel(ThumbnailViewModel thumbnailViewModel);
}
