package com.example.y1247.popoluar.appliction;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by y1247 on 2017/1/9.
 */

public class MyApplication extends Application {
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public static RequestQueue getHttpQueue() {
        return queue;
    }
}
