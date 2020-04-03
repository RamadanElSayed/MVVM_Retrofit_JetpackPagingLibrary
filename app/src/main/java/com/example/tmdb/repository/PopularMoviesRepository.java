package com.example.tmdb.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdb.common.Common;
import com.example.tmdb.factory.MovieDataSourceFactory;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieResponse;
import com.example.tmdb.service.ApiService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesRepository {
    private ArrayList<Movie> popularMovies = new ArrayList<>();
    private  ApiService apiService;
    private String apiKey;
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviePagedListLiveData;
    public PopularMoviesRepository(ApiService service, String apiKey) {
        this.apiService = service;
        this.apiKey = apiKey;
    }



    public MutableLiveData<List<Movie>> getMutableLiveData() {

        Call<MovieResponse> call = apiService.getPopularMovies(apiKey);
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

    public LiveData<PagedList<Movie>> getMovies() {
        MovieDataSourceFactory factory =new MovieDataSourceFactory(apiService);

        movieDataSourceLiveData =factory.getMovieDataSourceMutableLiveData();

        PagedList.Config config =(new PagedList.Config.Builder()).setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();


        executor = Executors.newFixedThreadPool(5);

        moviePagedListLiveData = (new LivePagedListBuilder<Long, Movie>(factory,config ))
                .setFetchExecutor(executor)
                .build();
        return moviePagedListLiveData;

    }
}
