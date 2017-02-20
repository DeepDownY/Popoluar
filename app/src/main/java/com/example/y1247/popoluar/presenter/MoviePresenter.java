package com.example.y1247.popoluar.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.y1247.popoluar.bean.Movie;
import com.example.y1247.popoluar.model.IMovieModel;
import com.example.y1247.popoluar.model.MovieModel;
import com.example.y1247.popoluar.view.IMovieView;

import java.util.List;

/**
 * Created by y1247 on 2017/1/9.
 */

public class MoviePresenter {
    public static final int TOP_RATED = 2;
    public static final int POPULAR = 1;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    List<Movie> ls = (List<Movie>) msg.obj;
                    setMovieList(ls);
                    break;
                case 200:
                    LoadFailed(msg.obj.toString());
            }
        }
    };

    private IMovieModel model;
    private IMovieView view;

    public MoviePresenter(IMovieView view) {
        this.view = view;
        this.model = new MovieModel(handler);
    }

    public void loadMovieList(int type){
        view.showProgress();
        model.getMovieList(type);
    }

    private void LoadFailed(String error){
        view.hideProgress();
        view.showLoadFailMsg(error);
    }

    private void setMovieList(List<Movie> ls){
        view.setMovieList(ls);
        view.hideProgress();
    }
}
