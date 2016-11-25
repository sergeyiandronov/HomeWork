package com.example.test2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button Check;
    EditText pas;
    EditText log;
    TextView res;
    String login="user";
    String password="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Check=(Button)findViewById(R.id.button);
        Check.setOnClickListener(this);
        log=(EditText)findViewById(R.id.editText);
        pas=(EditText)findViewById(R.id.editText2);
        res=(TextView)findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {


        if (!(log.getText().toString().equals(login))||!(pas.getText().toString().equals(password))){
           res.setTextColor(getResources().getColor(R.color.Red));
            res.setText(R.string.incor);


        }
        if (log.getText().toString().equals(login)){if (pas.getText().toString().equals(password)) {
            res.setTextColor(getResources().getColor(R.color.Green));
            res.setText(R.string.cor);

        }
        }
    }
}
