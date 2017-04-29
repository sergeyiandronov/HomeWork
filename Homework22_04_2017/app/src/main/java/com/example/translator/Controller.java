package com.example.translator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Сергей on 29.04.2017.
 */

public class Controller {
    static final String BASE_URL = "https://translate.yandex.net/";

    public static TrApi getApi() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        TrApi trApi = retrofit.create(TrApi.class);
        return trApi;
    }
}
