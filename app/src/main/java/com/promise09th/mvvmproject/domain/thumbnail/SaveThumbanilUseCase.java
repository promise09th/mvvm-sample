package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import io.reactivex.Completable;

public class SaveThumbanilUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    public SaveThumbanilUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Completable execute(Thumbnail savedThumbnail) {
        return thumbnailRepository.saveThumbnail(savedThumbnail);
    }
}
