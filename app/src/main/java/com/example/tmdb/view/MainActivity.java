package com.example.tmdb.view;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.tmdb.model.Movie;
import com.example.tmdb.model.MovieResponse;
import com.example.tmdb.service.ApiService;
import com.example.tmdb.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerViewMovies;
    private PopularMovieAdapter popularMovieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("TMDB POPULAR MOVIES");
        recyclerViewMovies = findViewById(R.id.rvMovies);

        getPopularMovies();
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();

            }
        });


    }

    private void getPopularMovies() {
        ApiService apiService = RetrofitInstance.getServiece();
        Call<MovieResponse> call = apiService.getPopularMovies(Common.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();

                if(movieResponse !=null && movieResponse.getResults() !=null){
                    movies = (ArrayList<Movie>) movieResponse.getResults();
                    setUi();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


    }

    private void setUi() {
        popularMovieAdapter = new PopularMovieAdapter(this,movies);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this,2));
        }else{
            recyclerViewMovies.setLayoutManager(new GridLayoutManager(this,4));

        }
        recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovies.setAdapter(popularMovieAdapter);

    }


}
