package com.promise09th.mvvmproject.presentation;

import android.util.Log;

import com.promise09th.mvvmproject.presentation.injection.component.DaggerAppComponent;

import java.io.IOException;
import java.net.SocketException;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

public class MvvmApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        setRxErrorHandler();
    }

    private void setRxErrorHandler() {
        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {
                e = e.getCause();
            }

            if ((e instanceof IOException) || (e instanceof SocketException)) {
                // fine, irrelevant network problem or API that throws on cancellation
                return;
            }

            if (e instanceof InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return;
            }

            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                // that's likely a bug in the application
                Thread.currentThread().getUncaughtExceptionHandler()
                        .uncaughtException(Thread.currentThread(), e);
                return;
            }

            if (e instanceof IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().getUncaughtExceptionHandler()
                        .uncaughtException(Thread.currentThread(), e);
                return;
            }
            Log.e("RxJava_HOOK", "Undeliverable exception received, not sure what to do" + e.getMessage());
        });
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
