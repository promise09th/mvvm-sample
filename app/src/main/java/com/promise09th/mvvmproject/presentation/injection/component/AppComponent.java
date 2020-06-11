package com.promise09th.mvvmproject.presentation.injection.component;

import android.app.Application;

import com.promise09th.mvvmproject.MvvmApplication;
import com.promise09th.mvvmproject.presentation.injection.module.ActivityModule;
import com.promise09th.mvvmproject.presentation.injection.module.AppModule;
import com.promise09th.mvvmproject.presentation.injection.module.DataSourceModule;
import com.promise09th.mvvmproject.presentation.injection.module.DbModule;
import com.promise09th.mvvmproject.presentation.injection.module.FragmentModule;
import com.promise09th.mvvmproject.presentation.injection.module.RepositoryModule;
import com.promise09th.mvvmproject.presentation.injection.module.RetrofitModule;
import com.promise09th.mvvmproject.presentation.injection.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

// Reference : https://android.jlelse.eu/7-steps-to-implement-dagger-2-in-android-dabc16715a3a

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        AppModule.class,
        DataSourceModule.class,
        DbModule.class,
        FragmentModule.class,
        RepositoryModule.class,
        RetrofitModule.class,
        ViewModelModule.class
})
public interface AppComponent extends AndroidInjector<MvvmApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    /*
     * This is our custom Application class
     * */
    void inject(MvvmApplication mvvmApplication);
}
