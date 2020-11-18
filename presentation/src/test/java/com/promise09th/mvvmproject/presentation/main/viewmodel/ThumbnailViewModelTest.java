package com.promise09th.mvvmproject.presentation.main.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.promise09th.mvvmproject.domain.model.ThumbnailDomain;
import com.promise09th.mvvmproject.domain.thumbnail.DeleteThumbnailUseCase;
import com.promise09th.mvvmproject.domain.thumbnail.GetAllThumbnailUseCase;
import com.promise09th.mvvmproject.domain.thumbnail.GetThumbanilUseCase;
import com.promise09th.mvvmproject.domain.thumbnail.SaveThumbanilUseCase;
import com.promise09th.mvvmproject.presentation.model.PresentationMapper;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;
import com.promise09th.mvvmproject.utils.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.stream.Collectors;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ThumbnailViewModelTest {

    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock private DeleteThumbnailUseCase deleteThumbnailUseCase;
    @Mock private GetAllThumbnailUseCase getAllThumbnailUseCase;
    @Mock private GetThumbanilUseCase getThumbnailUseCase;
    @Mock private SaveThumbanilUseCase saveThumbanilUseCase;

    @InjectMocks private ThumbnailViewModel thumbnailViewModel;

    @Before
    public void setUp() {
        setUpRxSchedulers();
    }

    private void setUpRxSchedulers() {
        RxJavaPlugins.setInitIoSchedulerHandler(c -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(c -> Schedulers.trampoline());
    }

    @Test
    public void fetchThumbnail() {
        ArrayList<ThumbnailDomain> list = new ArrayList<>();
        list.add(new ThumbnailDomain.Builder()
                .setName("IU")
                .setDateTIme("20201117")
                .setThumbnailUrl("")
                .setType(Constants.IMAGE_TYPE)
                .build());

        Mockito.when(getThumbnailUseCase.execute("IU")).thenReturn(Single.just(list));

        thumbnailViewModel.fetchThumbnail("IU");

        TestSubscriber<ArrayList<ThumbnailDomain>> testSubscriber = TestSubscriber.create();

        getThumbnailUseCase.execute("IU").toFlowable().subscribe(testSubscriber);

        ArrayList<Thumbnail> result = list.stream()
                .map(PresentationMapper::mapToThumbnail)
                .collect(Collectors.toCollection(ArrayList::new));

        assertEquals(thumbnailViewModel.getSearchResultThumbnail().getValue(), result);
    }

    @After
    public void tearDown() throws Exception {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }
}
