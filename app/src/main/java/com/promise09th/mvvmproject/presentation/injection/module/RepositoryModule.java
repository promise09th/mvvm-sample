package com.promise09th.mvvmproject.presentation.injection.module;

import com.promise09th.mvvmproject.data.thumbnail.ThumbnailLocalDataSource;
import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRemoteDataSource;
import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public ThumbnailRepository provideThumbnailRepository(ThumbnailLocalDataSource local,
                                                          ThumbnailRemoteDataSource remote) {
        return new ThumbnailRepository(local, remote);
    }
}
