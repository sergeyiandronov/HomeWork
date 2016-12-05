package com.example.test2016;

import android.content.Intent;
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
    TextView res;
    EditText log;
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
       Intent intent=new Intent(MainActivity.this,SecondAc.class);


        if (!(log.getText().toString().equals(login))||!(pas.getText().toString().equals(password))){
          res.setTextColor(getResources().getColor(R.color.Red));
            res.setText("Неверный логин или пароль");


        }
        if ((log.getText().toString().equals(login))&&(pas.getText().toString().equals(password))) {
          startActivityForResult(intent,1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data.getBooleanExtra("Ended",false)){

            this.finish();

        }
    }
}
