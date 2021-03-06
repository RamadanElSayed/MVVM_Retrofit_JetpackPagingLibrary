package com.example.tmdb.service;

import com.example.tmdb.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMoviesWithPaging(
            @Query("api_key") String apiKey,
            @Query("page") long page);
}
