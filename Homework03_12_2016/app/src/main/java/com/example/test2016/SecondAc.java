package com.example.test2016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

/**
 * Created by Сергей on 05.12.2016.
 */

public class SecondAc extends Activity implements View.OnClickListener {

    Button Exit;
    Button Close;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Exit=(Button)findViewById(R.id.button2);
        Close=(Button)findViewById(R.id.button3);
        Exit.setOnClickListener(this);
        Close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();

        switch (v.getId()) {
            case R.id.button2:
                setResult(RESULT_OK,intent);
                this.finish();
                break;
            case R.id.button3:
                intent.putExtra("Ended",true);
                setResult(RESULT_OK,intent);
                this.finish();
                break;
        }
    }
}
