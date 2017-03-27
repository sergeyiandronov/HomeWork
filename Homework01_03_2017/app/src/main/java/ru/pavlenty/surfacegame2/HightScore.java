package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HightScore extends AppCompatActivity {
    TextView textView,textView2,textView3,textView4;
    int[] highScore=new int[4];
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hight_score);

        //initializing the textViews
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        db=new DatabaseHelper(this);
        Cursor c=db.getScore();
        c.moveToFirst();
        for(int t=0;t <highScore.length;t++){
            if (!(c.isAfterLast())){
                highScore[t] =Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_ADD)));c.moveToNext(); }else{
                highScore[t]=0;
            }
        }

        //setting the values to the textViews
        textView.setText("1."+highScore[0]);
        textView2.setText("2."+highScore[1]);
        textView3.setText("3."+highScore[2]);
        textView4.setText("4."+highScore[3]);


    }
}
