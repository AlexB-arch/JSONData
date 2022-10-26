package com.example.somedata;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // Firebase
    private DatabaseReference databaseReference;

    // Widgets
    private EditText mEditText;
    private Button mButton;
    private TextView mTextView;
    private Button mAddUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = getInstance().getReference();

        mEditText = findViewById(R.id.writeText);
        mButton = findViewById(R.id.search_button);
        mTextView = findViewById(R.id.readText);
        mAddUserButton = findViewById(R.id.addUserButton);

        // Search button
        mButton.setOnClickListener(v -> {
            // Get the text
            String text = mEditText.getText().toString();
            // Search for the text
            databaseReference.child("users").equalTo(text).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Get the data
                    DataSnapshot dataSnapshot = task.getResult();
                    // Check if the data exists
                    if (dataSnapshot.exists()) {
                        // Get the data
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // Get the data
                            User user = snapshot.getValue(User.class);
                            // Check if the data is not null
                            if (user != null) {
                                // Get the data
                                String username = user.username;
                                String firstname = user.firstname;
                                String lastname = user.lastname;
                                String email = user.email;
                                // Do something with the data
                                mTextView.setText(username + " " + firstname + " " + lastname + " " + email);
                            }
                        }
                    }
                }
            });
        });

        // Add user button calls Add_User activity
        mAddUserButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Add_User.class)));
    }
}