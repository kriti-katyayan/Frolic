package com.tiaa.tiaaprepupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton=findViewById(R.id.loginBtn);
        signUp=findViewById(R.id.register);

        //goto sign up page
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regPage=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(regPage);
            }
        });

        //go to login page
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginPage =new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginPage);
            }
        });


    }
}