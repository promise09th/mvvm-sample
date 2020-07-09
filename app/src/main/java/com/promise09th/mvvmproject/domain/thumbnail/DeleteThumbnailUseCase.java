package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DeleteThumbnailUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    @Inject
    public DeleteThumbnailUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Completable execute(Thumbnail savedThumbnail) {
        return thumbnailRepository.deleteThumbnail(savedThumbnail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
