package com.example.tmdb;
import android.content.Context;
import com.example.tmdb.repository.PopularMoviesRepository;
import com.example.tmdb.service.ApiService;
import com.example.tmdb.service.RetrofitInstance;

public class Injection {

    public static PopularMoviesRepository provideMovieRepository(Context context) {
        return new PopularMoviesRepository(provideAPIService(), provideAPIKey(context));
    }

    private static ApiService provideAPIService() {
        return RetrofitInstance.createService(ApiService.class);
    }

    private static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }

}
