package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SaveThumbanilUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    @Inject
    public SaveThumbanilUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Completable execute(Thumbnail savedThumbnail) {
        return thumbnailRepository.saveThumbnail(savedThumbnail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
