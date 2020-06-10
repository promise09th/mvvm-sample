package com.promise09th.mvvmproject.presentation.di.component;

import android.app.Application;

import com.promise09th.mvvmproject.MvvmApplication;
import com.promise09th.mvvmproject.presentation.di.module.ActivityModule;
import com.promise09th.mvvmproject.presentation.di.module.AppModule;
import com.promise09th.mvvmproject.presentation.di.module.DataSourceModule;
import com.promise09th.mvvmproject.presentation.di.module.FragmentModule;
import com.promise09th.mvvmproject.presentation.di.module.RepositoryModule;
import com.promise09th.mvvmproject.presentation.di.module.RetrofitModule;
import com.promise09th.mvvmproject.presentation.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

// Reference : https://android.jlelse.eu/7-steps-to-implement-dagger-2-in-android-dabc16715a3a

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        AppModule.class,
        DataSourceModule.class,
        FragmentModule.class,
        RepositoryModule.class,
        RetrofitModule.class,
        ViewModelModule.class
})
public interface AppComponent {

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
