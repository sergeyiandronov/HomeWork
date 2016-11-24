package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Сергей on 22.11.2016.
 */

public class Activity2 extends Activity implements View.OnTouchListener {
    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        link=(TextView)findViewById(R.id.textView);
        link.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent intent=new Intent(Activity2.this,Activity3.class);
        startActivity(intent);
        return true;
    }
}
