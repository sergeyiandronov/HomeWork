package com.example.asynctaskh;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.util.concurrent.TimeUnit;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView ImView;
    Button btn;
  PictureSwitcher CatSwitcher;
    Drawable[] cats=new Drawable[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImView=(ImageView)findViewById(R.id.imageView);
        btn=(Button)findViewById(R.id.button);
        cats[0]=getResources().getDrawable(R.drawable.cat1);
        cats[1]=getResources().getDrawable(R.drawable.cat2);
        cats[2]=getResources().getDrawable(R.drawable.cat3);
    }
    public void StartCat(View v){
        CatSwitcher= new PictureSwitcher();
       CatSwitcher.execute();
    }
    public void CancelCat(View v){
        CatSwitcher.cancel(true);
    }
   class PictureSwitcher extends AsyncTask<Void,Integer,Void>{
       @Override
       protected void onPreExecute(){
          btn.setVisibility(View.GONE);
           btn.setEnabled(false);
       }

       @Override
       protected void  onProgressUpdate(Integer... params){
           ImView.setImageDrawable(cats[params[0]]);
       }

       @Override
       protected Void doInBackground(Void... params) {

           for(int counter=0;counter<3;  counter++){

             publishProgress(counter);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
           return null;
       }
       @Override
       protected void onPostExecute(Void result){
           btn.setVisibility(View.VISIBLE);
           btn.setEnabled(true);
           ImView.setImageResource(R.mipmap.ic_launcher);
       }
       @Override
       protected void onCancelled(){
           super.onCancelled();
           btn.setVisibility(View.VISIBLE);
           btn.setEnabled(true);
           ImView.setImageResource(R.mipmap.ic_launcher);
       }
   }
}
