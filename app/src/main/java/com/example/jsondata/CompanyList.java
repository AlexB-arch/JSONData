package com.example.jsondata;


import android.content.Context;
import android.content.res.Resources;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class CompanyList extends Context {

    public static final JSONArray Company_List = new JSONArray();
    public static final Map<String, JSONArray> Company_Map = new HashMap<>();

    private RequestQueue requestQueue = Volley.newRequestQueue(this);

    // Add request to the RequestQueue

    private static void addItem(CompanyList company){
    }

    @Override
    public Resources getResources() {
        return getResources().getString(R.string.url);
    }
}