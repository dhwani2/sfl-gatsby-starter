package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    public String username;
    public String password;
    public String name;
    public String accountType;

    DatabaseReference usersDatabase;
    DatabaseReference branchesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // get databases
        usersDatabase = FirebaseDatabase.getInstance().getReference("All users");
        branchesDatabase = FirebaseDatabase.getInstance().getReference("All branches");
    }

    public void createAccount(View view) {
        // Reset error message back if needed
        findViewById(R.id.errorMessage).setVisibility(View.INVISIBLE);

        // get first name, username and password inputted by user as a string
        EditText nameOfUser = (EditText)findViewById(R.id.nameTxt);
        name = nameOfUser.getText().toString().trim();

        // check if name has no symbols/numbers
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i))) {
                findViewById(R.id.errorMessage).setVisibility(View.VISIBLE);
                return;
            }
        }

        EditText userText = (EditText)findViewById(R.id.usernameTxtReg);
        username = userText.getText().toString().toLowerCase().trim();

        // check if username is one "word" (no spaces)
        String[] usernameLength = username.split(" ");
        if (usernameLength.length != 1) {
            findViewById(R.id.errorMessage).setVisibility(View.VISIBLE);
            return;
        }

        EditText pwdText = (EditText)findViewById(R.id.passwordTxtReg);
        password = pwdText.getText().toString();

        Spinner menu = (Spinner)findViewById(R.id.typeAccount);
        accountType = menu.getSelectedItem().toString();

        // at least one empty field
        if (name.length()==0 || username.length()==0 || password.length()==0 || accountType.equals("Pick an account type")) {
            findViewById(R.id.errorMessage).setVisibility(View.VISIBLE);
            return;
        }

        // since admin account is already created and can only have one
        if (username.equals("admin")) {
            findViewById(R.id.errorMessage).setVisibility(View.VISIBLE);
            return;
        }

        existingUser();
    }

    // check if user already exist in database. if exist, can't create account, else, create user instance and add to database
    public void existingUser() {
        usersDatabase.child(username).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    findViewById(R.id.errorMessage).setVisibility(View.VISIBLE);
                    return;
                }
                else {
                    User user = new User(username, password, name, accountType);
                    usersDatabase.child(username).setValue(user);

                    if (accountType.equals("Employee")) {
                        branchesDatabase.child(username).setValue(username);
                    }
                    // to pass as static variable over to different activity
                    WelcomeActivity.username = username;
                    Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}