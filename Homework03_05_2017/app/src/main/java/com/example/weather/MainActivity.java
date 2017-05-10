package com.example.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextView tv;
    ImageView im;

    String imgs="   h";
   private static WeatherApi wApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv1);
        im=(ImageView)findViewById(R.id.imageView);

       wApi= Controller.getApi();

      wApi.getIt().enqueue(new Callback<WW>() {
            @Override
            public void onResponse(Call<WW> call, Response<WW> response) {

                imgs=String.valueOf(response.body().getWeather().get(0).getIcon());
                 tv.setText(String.valueOf(response.body().getMain().getTemp()));
                wApi.getImg(imgs).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Bitmap btm= BitmapFactory.decodeStream(response.body().byteStream());

                        im.setImageBitmap(btm);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<WW> call, Throwable t) {

            }
        });

    }



    public void Click(View v){

        wApi.getIt().enqueue(new Callback<WW>() {
            @Override
            public void onResponse(Call<WW> call, Response<WW> response) {

                imgs=String.valueOf(response.body().getWeather().get(0).getIcon());
                tv.setText(String.valueOf(response.body().getMain().getTemp()));
                wApi.getImg(imgs).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Bitmap btm= BitmapFactory.decodeStream(response.body().byteStream());

                        im.setImageBitmap(btm);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<WW> call, Throwable t) {

            }
        });

}}
