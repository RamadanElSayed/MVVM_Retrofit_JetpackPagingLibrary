package com.example.tmdb.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.tmdb.common.Common;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieResponse;
import com.example.tmdb.service.ApiService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesRepository {
    private ArrayList<Movie> popularMovies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Movie>> getMutableLiveData() {

        ApiService apiService = RetrofitInstance.getServiece();
        Call<MovieResponse> call = apiService.getPopularMovies(Common.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();

                if(movieResponse !=null && movieResponse.getResults() !=null){
                    popularMovies = (ArrayList<Movie>) movieResponse.getResults();
                    mutableLiveData.setValue(popularMovies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
