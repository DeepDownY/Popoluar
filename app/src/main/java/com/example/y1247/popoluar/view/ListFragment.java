package com.example.y1247.popoluar.view;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.y1247.popoluar.adapter.MovieAdapter;
import com.example.y1247.popoluar.bean.Movie;
import com.example.y1247.popoluar.model.MovieModel;
import com.example.y1247.popoluar.presenter.MoviePresenter;
import com.example.y1247.popoluar.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by y1247 on 2017/1/8.
 */

public class ListFragment extends Fragment
        implements IMovieView ,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.rv_MovieList)
    RecyclerView rv_MovieList;
    @BindView(R.id.sl_mainLayout)
    SwipeRefreshLayout sl_mainLayout;

    private int sortType = 1;
    private int listType = 1;

    RecyclerView.LayoutManager mLayoutManager;

    MoviePresenter presenter;
    MovieAdapter adapter;
    int firstFlag = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.list_fragment,
                container,
                false
        );
        ButterKnife.bind(this,view);
        loadSetting();
        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(loadSetting()) {
            displaySetting();
        }
    }

    private void init() {

        presenter = new MoviePresenter(this);

        sl_mainLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );
        sl_mainLayout.setOnRefreshListener(this);

        setLayoutManager();
        rv_MovieList.setLayoutManager(mLayoutManager);
        rv_MovieList.setItemAnimator(new DefaultItemAnimator());

        adapter = new MovieAdapter(this.getActivity(),listType);
        adapter.setList(new ArrayList<Movie>());
        rv_MovieList.setAdapter(adapter);

        if(sortType == MovieModel.POPULAR){
            presenter.loadMovieList(MoviePresenter.POPULAR);
        }else{
            presenter.loadMovieList(MoviePresenter.TOP_RATED);
        }

    }

    @Override
    public void setMovieList(List<Movie> ls) {
        Log.i("sdf", "setMovieList: "+listType);
        if(!checkNet()){
            Toast.makeText(
                    getActivity(),
                    "No available NetWork,use cache",
                    Toast.LENGTH_SHORT).show();
        }
        adapter = new MovieAdapter(this.getActivity(),listType);

        setLayoutManager();
        rv_MovieList.setLayoutManager(mLayoutManager);
        adapter.setList(ls);
        rv_MovieList.setAdapter(adapter);
    }

    private void setLayoutManager() {
        if(this.listType== SettingsFragment.STAGGEREDGRID){
            mLayoutManager = new StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
            );
        }else {
            mLayoutManager = new LinearLayoutManager(
                    getActivity(),
                    LinearLayoutManager.VERTICAL,
                    false
            );
        }
    }

    @Override
    public void showProgress() {
        sl_mainLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        sl_mainLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg(String error) {
        Toast.makeText(this.getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        displaySetting();
    }

    private Boolean checkNet(){
        ConnectivityManager manager = (ConnectivityManager) getActivity().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info == null){
            return false;
        }
        return true;
    }

    private Boolean loadSetting() {
        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (setting != null) {
            String listType = setting.getString(SettingsFragment.LIST_KEY,"1");
            if(Integer.parseInt(listType)!=this.listType) {
                this.listType = Integer.parseInt(listType);
                return true;
            }
            String sortType = setting.getString(SettingsFragment.SORT_KEY,"1");
            if(Integer.parseInt(sortType)!=this.sortType) {
                this.sortType = Integer.parseInt(sortType);
                return true;
            }
        }
        return false;
    }

    private void displaySetting(){
        setLayoutManager();
        rv_MovieList.setLayoutManager(mLayoutManager);
        adapter = new MovieAdapter(this.getActivity(),listType);
        if(sortType == MovieModel.POPULAR){
            presenter.loadMovieList(MoviePresenter.POPULAR);
        }else{
            presenter.loadMovieList(MoviePresenter.TOP_RATED);
        }

    }
}
