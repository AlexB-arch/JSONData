package com.example.somedata;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.os.Bundle;
import android.util.Log;
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
    // Firebase URL
    private static final String FIREBASE_URL = "https://sample-data-app-default-rtdb.firebaseio.com";

    // Widgets
    private EditText mEditText;
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = getInstance().getReference();

        mEditText = findViewById(R.id.writeText);
        mButton = findViewById(R.id.search_button);
        mTextView = findViewById(R.id.readText);

        // Search button
        mButton.setOnClickListener(v -> {
            String text = mEditText.getText().toString();
            if (!text.isEmpty()) {
                // Search for the text
                search(text);
            }
        });

        // Add user button
        findViewById(R.id.addUserButton).setOnClickListener(v -> {
            // Create a new user
            User user = new User("John", "email address");
            // Add the user to the database
            databaseReference.child("users").child("1").setValue(user);
        });
    }

    // Search for the specified text
    private void search(String text) {
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
                            String email = user.email;
                            // Log the data
                            Log.d(TAG, "search: username: " + username);
                            Log.d(TAG, "search: email: " + email);

                            // Display the data
                            mTextView.setText("username: " + username + " email: " + email);
                        }
                    }
                }
            }
        });
    }

    // Adds a new user to the database
    private void addUser() {
        // Create a new user
        User user = new User("John", "myemail");
        // Add the user to the database
        databaseReference.child("users").push().setValue(user);
    }
}