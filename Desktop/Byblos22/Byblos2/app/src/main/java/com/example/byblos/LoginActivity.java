package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    public String username;
    public String password;

    DatabaseReference usersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get database
        usersDatabase = FirebaseDatabase.getInstance().getReference("All users");
    }

    public void checkUser(View view) {
        // Reset error message back if needed
        findViewById(R.id.loginError).setVisibility(View.INVISIBLE);

        // get username and password inputted by user as a string
        EditText userText = (EditText)findViewById(R.id.usernameTxt);
        username = userText.getText().toString().toLowerCase();

        EditText pwdText = (EditText)findViewById(R.id.passwordPassText);
        password = pwdText.getText().toString();

        if (username.length() == 0 || password.length() == 0) {
            findViewById(R.id.loginError).setVisibility(View.VISIBLE);
            return;
        }

        // for admin account
        if (username.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(this, AdminWelcomeActivity.class);
            startActivity(intent);
        }
        else if (username.equals("admin") && password.equals("admin") == false) {
            findViewById(R.id.loginError).setVisibility(View.VISIBLE);
            return;
        }

        // check if account exist in database
        else {
            existingUser();
        }
    }

    // check if user already exist in database. if exist, login, else, show error message
    public void existingUser() {
        Log.d("test0", "in");
        usersDatabase.child(username).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.getPassword().equals(password)) {
                        // to pass as static variable over to different activity
                        WelcomeActivity.username = username;
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        findViewById(R.id.loginError).setVisibility(View.VISIBLE);
                        return;
                    }
                }
                else {
                    findViewById(R.id.loginError).setVisibility(View.VISIBLE);
                    return;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}