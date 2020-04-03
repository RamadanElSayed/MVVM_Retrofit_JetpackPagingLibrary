package com.example.tmdb.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.tmdb.model.Movie;
import com.example.tmdb.repository.MovieDataSource;
import com.example.tmdb.service.ApiService;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private Application application;
    private ApiService apiService;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;

    public MovieDataSourceFactory(ApiService apiService) {
        this.apiService = apiService;
        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }


    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(apiService);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
