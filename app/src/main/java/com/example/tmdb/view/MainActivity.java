package com.example.tmdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.tmdb.R;
import com.example.tmdb.adapter.PopularMovieAdapter;
import com.example.tmdb.common.Common;
import com.example.tmdb.databinding.ActivityMainBinding;
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieResponse;
import com.example.tmdb.service.ApiService;
import com.example.tmdb.service.RetrofitInstance;
import com.example.tmdb.viewmodel.PopularMoviesViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PagedList<Movie> movies;
    private RecyclerView recyclerViewMovies;
    private PopularMovieAdapter popularMovieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PopularMoviesViewModel popularMoviesViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("TMDB POPULAR MOVIES");
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        popularMoviesViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);

        getPopularMovies();


        swipeRefreshLayout = activityMainBinding.swipe;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });


    }

    private void getPopularMovies() {
//        popularMoviesViewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> moviesFromLiveData) {
//                movies = (ArrayList<Movie>)moviesFromLiveData;
//                setUi();
//
//            }
//        });


        popularMoviesViewModel.getMoviePagedListLiveData().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> moviePagedListLiveData) {
                movies = moviePagedListLiveData;
                setUi();
            }
        });

    }

    private void setUi() {
        recyclerViewMovies = activityMainBinding.rvMovies;

        popularMovieAdapter = new PopularMovieAdapter(this);

        popularMovieAdapter.submitList(movies);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 4));

        }
        recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovies.setAdapter(popularMovieAdapter);

    }


}
