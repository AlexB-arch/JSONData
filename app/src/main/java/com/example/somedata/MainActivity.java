package com.example.somedata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // API
    private API api;

    // Widgets
    private EditText mEditText;
    private Button mButton;
    private Button mAddUserButton;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // API
        api = new API();

        // Widgets
        mEditText = findViewById(R.id.writeText);
        mButton = findViewById(R.id.search_button);
        mAddUserButton = findViewById(R.id.addUserButton);
        mRecyclerView = findViewById(R.id.user_list);

        // Get the users from the database and display them in the recycler view when the activity start.

        // Add user button calls Add_User activity
        mAddUserButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Add_User.class)));
    }
}