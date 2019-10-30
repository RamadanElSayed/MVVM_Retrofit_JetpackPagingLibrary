package com.example.tmdb.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.tmdb.common.Common;
import com.example.tmdb.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb.R;

public class DetailsActivity extends AppCompatActivity {

    private ImageView poster;
    private Movie movie;
    private TextView title,synopsis,rating,releaseDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        poster = findViewById(R.id.ivMovieLarge);
        title = findViewById(R.id.tvMovieTitle);
        synopsis = findViewById(R.id.tvPlotsynopsis);
        rating = findViewById(R.id.tvMovieRating);
        releaseDate = findViewById(R.id.tvReleaseDate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if(intent.hasExtra("movie")){
            movie = getIntent().getParcelableExtra("movie");

            Glide.with(this)
                    .load(Common.IMG_PATH+movie.getPosterPath())
                    .into(poster);
        getSupportActionBar().setTitle(movie.getOriginalTitle());

        title.setText(movie.getOriginalTitle());
        synopsis.setText(movie.getOverview());
        rating.setText(movie.getVoteAverage().toString());
        releaseDate.setText(movie.getReleaseDate());

        }

    }

}
