package com.example.somedata;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DatabaseReference databaseReference;
    private Button search;
    private EditText searchUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = getInstance().getReference(getResources().getString(R.string.json_url));

        searchUser = findViewById(R.id.writeText);
        search = findViewById(R.id.search_button);

        search.setOnClickListener((View.OnClickListener) readUser(String.valueOf(searchUser)));

    }

    public void writeNewUser(String userId, String name, String email){
        User user = new User(name, email);

        databaseReference.child("users").child(userId).setValue(user);
    }

    public CharSequence readUser(String userId){
        databaseReference.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()){
                    Log.e("firebase", "Error getting data.", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        return (CharSequence) databaseReference.child("users").child(userId).get();
    }

    public void updateUser(String userId, String newName){
        databaseReference.child("users").child(userId).child("name").setValue(newName);
    }

    public void deleteUser(String userId){
        databaseReference.child("users").child(userId).removeValue();
    }
}