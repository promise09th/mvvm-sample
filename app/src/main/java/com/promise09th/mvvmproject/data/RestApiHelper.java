package com.promise09th.mvvmproject.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.promise09th.mvvmproject.common.KakaoApiKey;
import com.promise09th.mvvmproject.model.ImageThumbnail;
import com.promise09th.mvvmproject.model.Thumbnail;
import com.promise09th.mvvmproject.model.VideoThumbnail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RestApiHelper {
    private static String TAG = RestApiHelper.class.getSimpleName();

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final String SEARCH_VIDEO = "https://dapi.kakao.com/v2/search/vclip";
    private static final String SEARCH_IMAGE = "https://dapi.kakao.com/v2/search/image";

    public static Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(SEARCH_VIDEO).newBuilder();
        urlBuilder.addQueryParameter("query", query);
        String requestUrl = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .addHeader("Authorization", "KakaoAK " + KakaoApiKey.REST_API_KEY)
                .url(requestUrl)
                .build();
        return get(request)
                .map(json -> new ArrayList<>(parseVideoThumbnail(json)));
    }

    private static ArrayList<VideoThumbnail> parseVideoThumbnail(String json) {
        try {
            Gson gson = new Gson();
            return Optional.ofNullable(new JsonParser().parse(json))
                    .map(JsonElement::getAsJsonObject)
                    .map(e -> e.getAsJsonArray("documents"))
                    .map(JsonElement::toString)
                    .map(documents -> gson.fromJson(documents, VideoThumbnail[].class))
                    .map(a -> new ArrayList<>(Arrays.asList(a)))
                    .orElse(new ArrayList<>());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(SEARCH_IMAGE).newBuilder();
        urlBuilder.addQueryParameter("query", query);
        String requestUrl = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .addHeader("Authorization", "KakaoAK " + KakaoApiKey.REST_API_KEY)
                .url(requestUrl)
                .build();
        return get(request)
                .map(json -> new ArrayList<>(parseImageThumbnail(json)));
    }

    private static ArrayList<ImageThumbnail> parseImageThumbnail(String json) {
        try {
            Gson gson = new Gson();
            return Optional.ofNullable(new JsonParser().parse(json))
                    .map(JsonElement::getAsJsonObject)
                    .map(e -> e.getAsJsonArray("documents"))
                    .map(JsonElement::toString)
                    .map(documents -> gson.fromJson(documents, ImageThumbnail[].class))
                    .map(a -> new ArrayList<>(Arrays.asList(a)))
                    .orElse(new ArrayList<>());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static Single<String> get(Request request) {
        return Single.<String>create(e -> {
            try {
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException ex) {
                        e.onError(ex);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        ResponseBody body = response.body();
                        String result = body != null ? body.string() : "";
                        Log.d(TAG, "Rest API Result (Get) : " + result);
                        e.onSuccess(result);
                    }
                });
            } catch (IllegalArgumentException ex) {
                e.onError(ex);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

