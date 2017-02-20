package com.example.y1247.popoluar.model;

import android.os.Handler;
import android.util.Log;

import com.example.y1247.popoluar.bean.Movie;
import com.example.y1247.popoluar.BuildConfig;
import com.example.y1247.popoluar.buiness.JsonGetter;

import java.util.List;

/**
 * Created by y1247 on 2017/1/8.
 */

public class MovieModel implements IMovieModel{
    public static final int TOP_RATED = 2;
    public static final int POPULAR = 1;
    private String APIKEY =  BuildConfig.APIKEY;

    List<Movie> ls;

    private static final String TYPE_TOP_RATED =
            "https://api.themoviedb.org/3/movie/top_rated?language=zh&api_key=";
    private static final String TYPE_POPULAR =
            "https://api.themoviedb.org/3/movie/popular?language=zh&api_key=";

    private JsonGetter jsonGetter;

    private Handler handler ;

    public MovieModel(Handler handler) {
        this.jsonGetter = new JsonGetter();
        this.handler = handler;
    }

    @Override
    public void getMovieList(int type) {
        Log.i("tag", "getMovieList: ");
        String temp;

        if(type==TOP_RATED){
            temp = TYPE_TOP_RATED;
        }else
            temp = TYPE_POPULAR;

        temp = temp + APIKEY;
        jsonGetter.getMovieList(temp,handler);
    }
}
