package com.example.somedata;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// API class to handle the database
public class API {
    // Firebase
    private DatabaseReference database;

    // Constructor
    public API() {
        // Get the database reference
        database = FirebaseDatabase.getInstance().getReference();
    }

    // Search for a user
    public void search(String text) {
        // Search for the text
        database.child("users").equalTo(text).get().addOnCompleteListener(task -> {
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
                        }
                    }
                }
            }
        });
    }

    // Add a user
    public void addUser(String username, String firstname, String lastname, String email) {
        // Create the user
        User user = new User(username, firstname, lastname, email);
        // Add the user to the database
        database.child("users").child(username).setValue(user);
    }
}
