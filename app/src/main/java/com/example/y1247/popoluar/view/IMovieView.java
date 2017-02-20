package com.example.y1247.popoluar.view;

import com.example.y1247.popoluar.bean.Movie;

import java.util.List;

/**
 * Created by y1247 on 2017/1/8.
 */

public interface IMovieView {
    public void setMovieList(List<Movie> ls);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg(String error);
}
