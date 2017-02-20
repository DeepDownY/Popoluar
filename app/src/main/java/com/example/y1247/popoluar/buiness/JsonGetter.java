package com.example.y1247.popoluar.buiness;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.y1247.popoluar.bean.Movie;
import com.example.y1247.popoluar.appliction.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by y1247 on 2017/1/9.
 */

public class JsonGetter implements Response.Listener<JSONObject>,Response.ErrorListener{
    private RequestQueue queue;
    JSONObject json;
    Handler handler;

    public Boolean getMovieList(String url, Handler handler){
        this.handler = handler;
        queue = MyApplication.getHttpQueue();
        JsonObjectRequest request = new JsonObjectRequest(
                url, null, this, this);
        queue.add(request);
        return null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Message msg = new Message();
        msg.setTarget(handler);
        msg.what = 200;
        msg.obj = error.toString();
        msg.sendToTarget();
    }

    @Override
    public void onResponse(JSONObject response) {
        Message msg = new Message();
        List<Movie> ls = JsonToList(response);

        if(ls == null){
            msg.what = 200;
        }else {
            msg.what =1;
            msg.obj = ls;
        }
        msg.setTarget(handler);
        msg.sendToTarget();
    }

    private List<Movie> JsonToList(JSONObject json){
        List<Movie> ls;
        JSONArray jsonArray;

        Gson gson = new Gson();
        Type it = new TypeToken<List<Movie>>(){}.getType();
        try {
            jsonArray = json.getJSONArray("results");
            ls = gson.fromJson(jsonArray.toString(),it);
            return ls;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
