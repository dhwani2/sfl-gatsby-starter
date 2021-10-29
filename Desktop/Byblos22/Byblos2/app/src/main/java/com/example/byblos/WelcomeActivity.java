package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {

    // each user only has one username (will be used to get data from key)
    public static String username;

    DatabaseReference usersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // get database
        usersDatabase = FirebaseDatabase.getInstance().getReference("All users");

        // print welcome message
        TextView welcome = (TextView)findViewById(R.id.welcomeText);

        usersDatabase.child(username).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                welcome.setText("Welcome " + user.getName() + "!\nYou are logged in as " + user.getRole() + ".");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}