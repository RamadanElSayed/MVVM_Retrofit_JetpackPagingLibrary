package com.example.tmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdb.R;
import com.example.tmdb.common.Common;
import com.example.tmdb.model.Movie;
import com.example.tmdb.view.DetailsActivity;

import java.util.ArrayList;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public PopularMovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.name.setText(movies.get(position).getOriginalTitle());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        Glide.with(context)
                .load(Common.IMG_PATH + movies.get(position).getPosterPath())
                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView poster;
        public TextView name, rating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.movieName);
            rating = itemView.findViewById(R.id.movieRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Movie selectedMovie = movies.get(position);
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        context.startActivity(intent);
                     }
                }
            });
        }
    }
}
