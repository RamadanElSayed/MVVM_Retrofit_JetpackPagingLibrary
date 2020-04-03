package com.example.tmdb.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tmdb.repository.PopularMoviesRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private PopularMoviesRepository repository;

    public MainViewModelFactory(PopularMoviesRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PopularMoviesViewModel(repository);
    }
}
