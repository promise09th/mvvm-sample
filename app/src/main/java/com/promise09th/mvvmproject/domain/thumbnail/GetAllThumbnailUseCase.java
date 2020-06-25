package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import java.util.ArrayList;

import io.reactivex.Flowable;

public class GetAllThumbnailUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    public GetAllThumbnailUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Flowable<ArrayList<Thumbnail>> execute() {
        return thumbnailRepository.getAllThumbnail();
    }
}
