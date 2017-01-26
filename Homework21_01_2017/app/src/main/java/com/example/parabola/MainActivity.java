package com.example.parabola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout mLayout;
    EditText aa;
    EditText bb;
    EditText cc;
    ParabolaCanvas g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout=(RelativeLayout)findViewById(R.id.activity_main);
        g=new ParabolaCanvas(this);
        mLayout.addView(g);
        aa=(EditText)findViewById(R.id.editText6);
        bb=(EditText)findViewById(R.id.editText7);
        cc=(EditText)findViewById(R.id.editText8);

    }
    public void Drawn(View v){
        g.a=Integer.parseInt(aa.getText().toString());
        g.b=Integer.parseInt(bb.getText().toString());
        g.c=Integer.parseInt(cc.getText().toString());
        mLayout.invalidate();
    }
}
