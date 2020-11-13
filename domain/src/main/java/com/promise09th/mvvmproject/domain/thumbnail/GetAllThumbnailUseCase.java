package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.domain.model.DomainMapper;
import com.promise09th.mvvmproject.domain.model.ThumbnailDomain;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetAllThumbnailUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    @Inject
    public GetAllThumbnailUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Flowable<ArrayList<ThumbnailDomain>> execute() {
        return thumbnailRepository.getAllThumbnail()
                .map(list -> list.stream()
                        .map(DomainMapper::mapToThumbnailDomain)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
