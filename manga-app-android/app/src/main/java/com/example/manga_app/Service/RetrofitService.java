package com.example.manga_app.Service;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService() {
        createRetrofit();
    }

    private void createRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // Increase connection timeout
                .readTimeout(30, TimeUnit.SECONDS)    // Increase read timeout
                .writeTimeout(30, TimeUnit.SECONDS)   // Increase write timeout
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.16:8085/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
