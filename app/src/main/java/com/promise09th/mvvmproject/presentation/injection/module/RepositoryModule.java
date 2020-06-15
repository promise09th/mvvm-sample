package com.promise09th.mvvmproject.presentation.injection.module;

import com.promise09th.mvvmproject.data.source.ThumbnailLocalDataSource;
import com.promise09th.mvvmproject.data.source.ThumbnailRemoteDataSource;
import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;

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
