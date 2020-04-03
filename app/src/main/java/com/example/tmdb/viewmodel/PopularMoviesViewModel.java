package com.example.tmdb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.tmdb.model.Movie;
import com.example.tmdb.repository.PopularMoviesRepository;

import java.util.List;

public class PopularMoviesViewModel extends ViewModel {

    private LiveData<List<Movie>> PopularMoviesLiveData;

    private LiveData<PagedList<Movie>> moviePagedListLiveData;

    public PopularMoviesViewModel(PopularMoviesRepository popularMoviesRepository) {

        moviePagedListLiveData = popularMoviesRepository.getMovies();
        PopularMoviesLiveData = popularMoviesRepository.getMutableLiveData();
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return PopularMoviesLiveData;
    }

    public LiveData<PagedList<Movie>> getMoviePagedListLiveData() {
        return moviePagedListLiveData;
    }
}
