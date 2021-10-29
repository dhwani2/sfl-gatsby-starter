package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLoginActivity(View view){
        //Define the intent
        Intent intent = new Intent(this, LoginActivity.class);
        //Opens the login page
        startActivity(intent);

    }
    public void openRegisterActivity(View view){
        //Define the intent
        Intent intent = new Intent(this, RegisterActivity.class);
        //Opens the register page
        startActivity(intent);
    }
}