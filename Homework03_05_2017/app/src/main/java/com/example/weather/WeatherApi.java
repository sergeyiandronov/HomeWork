package com.example.weather;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Сергей on 08.05.2017.
 */

public interface  WeatherApi {
    @GET("/data/2.5/weather?id=524901&appid=38359dce3962f66a606a8ba5164a3ac2&units=metric")
    Call<WW> getIt();
    @GET("/img/w/{picnum}.png")
    Call<ResponseBody> getImg(@Path("picnum") String pn);
}
