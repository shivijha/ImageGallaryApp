package com.example.task1;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

class FlickrApi {
    interface RecentPhotosCallback {
        void onReceive(List<Photo> photos);
    }

    static void getRecentPhotos(RecentPhotosCallback callback) {
        api.getRecentPhotos().enqueue(onResult(callback));
    }

    @Keep
    private static class Result {
        @SerializedName("photos")
        Photos photos;

        @Keep
        static class Photos {
            @SerializedName("photo")
            List<Photo> photo;
        }
    }

    private static final Api api = new Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api.class);


    private interface Api {
        @GET("?method=flickr.photos.getRecent" +
                "&per_page=1000&api_key=6f102c62f41998d151e5a1b48713cf13" +
                "&format=json&nojsoncallback=1&extras=url_s&page=1")
        Call<Result> getRecentPhotos();
    }

    private static retrofit2.Callback<Result> onResult(RecentPhotosCallback callback) {
        return new retrofit2.Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                assert response.body() != null;
                callback.onReceive(response.body().photos.photo);
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
            }
        };
    }
}
