package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import java.util.ArrayList;

import io.reactivex.Single;

public class GetThumbanilUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    public GetThumbanilUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Single<ArrayList<Thumbnail>> execute(String query) {
        return thumbnailRepository.getThumbnail(query);
    }
}
