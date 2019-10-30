package com.example.tmdb.service;

import com.example.tmdb.common.Common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;


    public static ApiService getServiece(){
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Common.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();



        }

        return retrofit.create(ApiService.class);
    }
}
