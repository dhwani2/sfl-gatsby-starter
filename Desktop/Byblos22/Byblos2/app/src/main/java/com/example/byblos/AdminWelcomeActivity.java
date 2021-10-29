package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminWelcomeActivity extends AppCompatActivity {

    String username = "admin";
    String role = "administrator";

    //Button deleteBranchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_welcome);

        // print welcome message
        TextView welcome = (TextView)findViewById(R.id.adminWelcomeText);

        welcome.setText("Welcome " + username + "!\nYou are logged in as " + role + ".");
        /*
        deleteBranchButton = findViewById(R.id.deleteBranchButton);

        deleteBranchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AdminWelcomeActivity.this, DeleteBranch.class);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startActivity(intent);
                    }
                }, 5000);   //5 seconds

            }
        });

         */



    }

    public void openDeleteBranch(View view){
        //Define the intent
        Intent intent = new Intent(this, DeleteBranch.class);
        //Opens the login page
        startActivity(intent);

    }

    public void openDeleteCustomerAccounts(View view){
        //Define the intent
        Intent intent = new Intent(this, DeleteCustomerAccounts.class);
        //Opens the login page
        startActivity(intent);

    }




}