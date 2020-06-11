package com.promise09th.mvvmproject.presentation.injection.module;

import com.promise09th.mvvmproject.presentation.main.MyLockerFragment;
import com.promise09th.mvvmproject.presentation.main.SearchResultFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    public abstract MyLockerFragment contributeMyLockerFragment();

    @ContributesAndroidInjector
    public abstract SearchResultFragment contributeSearchResultFragment();
}
