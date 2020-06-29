package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import javax.inject.Inject;

import io.reactivex.Completable;

public class DeleteThumbnailUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    @Inject
    public DeleteThumbnailUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Completable execute(Thumbnail savedThumbnail) {
        return thumbnailRepository.deleteThumbnail(savedThumbnail);
    }
}
