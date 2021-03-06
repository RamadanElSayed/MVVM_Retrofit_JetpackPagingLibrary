package com.example.tmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb.R;
import com.example.tmdb.common.Common;
import com.example.tmdb.databinding.MovieItemBinding;
import com.example.tmdb.model.Movie;
import com.example.tmdb.view.DetailsActivity;

import java.util.ArrayList;

public class PopularMovieAdapter extends PagedListAdapter<Movie, PopularMovieAdapter.MovieViewHolder> {

    private Context context;

    public PopularMovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new MovieViewHolder(movieItemBinding);
    }

    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.movieItemBinding.setMovie(movie);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding movieItemBinding;

        public MovieViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;


            movieItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Movie selectedMovie = getItem(position);
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("movie", selectedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
