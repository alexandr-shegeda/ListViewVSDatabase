package com.example.myapplication.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
* Created by alexandr on 03.04.15.
*/
public class ApiHelper{

    Context context;

    public ApiHelper(Context context) {
        this.context = context;
    }

    public void getData(String data)
    {
        String url = "https://www.googleapis.com/customsearch/v1?" +
                "key=AIzaSyBmSXUzVZBKQv9FJkTpZXn0dObKgEQOIFU&cx=014099860786446192319:" +
                "t5mr0xnusiy&q=AndroidDev&alt=json&searchType=image";

        RequestQueue queue = Volley.newRequestQueue(context);

        Request<String> request = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("HttpStatusCode:",""+volleyError.networkResponse.statusCode);
            }
        });
    }

}
