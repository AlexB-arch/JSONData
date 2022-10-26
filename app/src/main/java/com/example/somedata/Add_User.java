package com.example.somedata;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Add_User extends AppCompatActivity {
    // Widgets
    private EditText mUsername;
    private EditText mFirstname;
    private EditText mLastname;
    private EditText mEmail;
    private Button mAddUserButton;

    // Calls the API
    private API api;

    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // Widgets
        mUsername = findViewById(R.id.add_username);
        mFirstname = findViewById(R.id.add_firstname);
        mLastname = findViewById(R.id.add_lastname);
        mEmail = findViewById(R.id.add_email);
        mAddUserButton = findViewById(R.id.add_button);

        // API
        api = new API();

        // Add user button
        mAddUserButton.setOnClickListener(v -> {
            // Get the data
            String username = mUsername.getText().toString();
            String firstname = mFirstname.getText().toString();
            String lastname = mLastname.getText().toString();
            String email = mEmail.getText().toString();
            // Add the user
            api.addUser(username, firstname, lastname, email);
        });
    }

}
