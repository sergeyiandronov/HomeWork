package com.example.weather;

/**
 * Created by Сергей on 08.05.2017.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Acer on 21.04.2017.
 */

public class Controller {
    static final String BASE_URL = "http://api.openweathermap.org/";

    public static WeatherApi getApi() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        WeatherApi wApi = retrofit.create(WeatherApi.class);
        return wApi;
    }
}