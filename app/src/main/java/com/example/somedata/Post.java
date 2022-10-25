package com.example.somedata;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Post {

    public String uid, author, title, body;
    public int rating = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post(){
        // Default constructor.
    }

    public Post(String uid, String author, String title, String body){
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("rating", rating);
        result.put("stars", stars);

        return result;
    }
}
