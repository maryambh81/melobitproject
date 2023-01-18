package com.example.melobitapplication.Api;

import com.example.melobitapplication.model.DetailSongModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("v1/song/{songId}")
    Call<DetailSongModel> getDetailSong(@Path("songId") String songId);

    @GET("v1/search/query/{key}/0/50")
    Call<ResponseBody> searchSongs(@Path("key") String key);

    @GET(" ")
    Call<ResponseBody> getSongs();
}
