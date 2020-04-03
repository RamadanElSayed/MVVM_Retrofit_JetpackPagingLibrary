package com.example.tmdb.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdb.common.Common;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieResponse;
import com.example.tmdb.service.ApiService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private ApiService apiService;
    private Application application;

    public MovieDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback callback) {

        Call<MovieResponse> call = apiService.getPopularMoviesWithPaging(Common.API_KEY, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                movies = (ArrayList<Movie>)movieResponse.getResults();

                if(movieResponse != null && movieResponse.getResults() != null){
                callback.onResult(movies, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {
        Call<MovieResponse> call = apiService.getPopularMoviesWithPaging(Common.API_KEY, params.key);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> movies;
                if(movieResponse!=null && movieResponse.getResults() !=null){

                movies = (ArrayList<Movie>) movieResponse.getResults();
                callback.onResult(movies, params.key + 1);

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
