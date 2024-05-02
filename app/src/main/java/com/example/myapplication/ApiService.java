package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface ApiService {
    @GET("songs")
    Call<List<Song>> getAllSongs();

    @GET("songs/genre/{genre}")
    Call<List<Song>> getSongsByGenre(@Path("genre") String genre);

    @GET("songs/{id}")
    Call<Song> getSongById(@Path("id") String id);

    @GET("comments/song/{songId}")
    Call<List<Comment>> getAllCommentsForSong(@Path("songId") String songId);
    @GET("ratings/song/{songId}")
    Call<List<Rating>> getRatingsBySongId(@Path("songId") String songId);

    @GET("songs/genres")
    Call<List<String>> getCategories();

    @POST("comments")
    Call<ApiResult> postComment(@Body Comment comment);

    @POST("ratings")
    Call<ApiResult> postRating(@Body Rating rating);
}
