package com.promise09th.mvvmproject.presentation.injection.module;

import com.promise09th.mvvmproject.presentation.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    public abstract MainActivity contributeMainActivity();
}
