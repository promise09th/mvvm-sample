package com.promise09th.mvvmproject.domain.thumbnail;

import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.domain.UseCase;
import com.promise09th.mvvmproject.domain.model.DomainMapper;
import com.promise09th.mvvmproject.domain.model.ThumbnailDomain;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetThumbanilUseCase implements UseCase {

    private ThumbnailRepository thumbnailRepository;

    @Inject
    public GetThumbanilUseCase(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    public Single<ArrayList<ThumbnailDomain>> execute(String query) {
        return thumbnailRepository.getThumbnail(query)
                .map(list -> list.stream()
                        .map(DomainMapper::mapToThumbnailDomain)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
