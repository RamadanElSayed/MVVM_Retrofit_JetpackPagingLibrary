package com.example.tmdb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdb.model.Movie;
import com.example.tmdb.repository.PopularMoviesRepository;

import java.util.List;

public class PopularMoviesViewModel extends AndroidViewModel {

    private PopularMoviesRepository popularMoviesRepository;


    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        popularMoviesRepository = new PopularMoviesRepository();
    }

    public LiveData<List<Movie>> getPopularMovies(){
        return popularMoviesRepository.getMutableLiveData();
    }
}
