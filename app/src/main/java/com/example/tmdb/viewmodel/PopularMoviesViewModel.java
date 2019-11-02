package com.example.tmdb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.tmdb.factory.MovieDataSourceFactory;
import com.example.tmdb.model.Movie;
import com.example.tmdb.repository.MovieDataSource;
import com.example.tmdb.repository.PopularMoviesRepository;
import com.example.tmdb.service.ApiService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;

public class PopularMoviesViewModel extends AndroidViewModel {

    private PopularMoviesRepository popularMoviesRepository;

    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviePagedListLiveData;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        popularMoviesRepository = new PopularMoviesRepository();

        ApiService apiService = RetrofitInstance.getServiece();
        MovieDataSourceFactory factory =new MovieDataSourceFactory(apiService,application);

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

    }

    public LiveData<List<Movie>> getPopularMovies(){
        return popularMoviesRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getMoviePagedListLiveData() {
        return moviePagedListLiveData;
    }
}
