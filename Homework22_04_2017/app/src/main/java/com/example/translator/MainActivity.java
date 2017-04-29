package com.example.translator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.logging.Logger.global;

public class MainActivity extends AppCompatActivity {
EditText totranslate;
    Translat otvet;

    private static TrApi trApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totranslate=(EditText)findViewById(R.id.editText2);
        trApi=Controller.getApi();
    }
    public void Click(View v){

        trApi.getIt(totranslate.getText().toString()).enqueue(new Callback<Translat>() {

            @Override
            public void onResponse(Call<Translat> call, Response<Translat> response) {
                otvet=response.body();
                totranslate.setText(" ");
                boolean firsttime=true;
                for(String h:otvet.getText()){
                    if(firsttime)totranslate.setText(h);
                    if(!firsttime)totranslate.setText(totranslate.getText().toString()+","+h);
                    firsttime=false;
                }

            }

            @Override
            public void onFailure(Call<Translat> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
