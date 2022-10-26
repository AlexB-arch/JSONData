package com.example.somedata;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// API class to handle the database
public class API {
    // Firebase
    private final DatabaseReference database;

    // Constructor
    public API() {
        // Get the database reference
        database = FirebaseDatabase.getInstance().getReference();
    }

    // Add a user
    public void addUser(String username, String firstname, String lastname, String email) {
        // Create the user
        User user = new User(username, firstname, lastname, email);
        // Add the user to the database
        database.child("users").child(username).setValue(user);
    }

    // Get a user
    public User getUser(String username) {
        // Get the user from the database
        Task<DataSnapshot> user = database.child("users").child(username).getRef().get();
        // Return the user
        return user.getResult().getValue(User.class);
    }

    // Update a user
    public void updateUser(String username, String firstname, String lastname, String email) {
        // Create the user
        User user = new User(username, firstname, lastname, email);
        // Update the user in the database
        database.child("users").child(username).setValue(user);
    }

    // Delete a user
    public void deleteUser(String username) {
        // Delete the user from the database
        database.child("users").child(username).removeValue();
    }
}
