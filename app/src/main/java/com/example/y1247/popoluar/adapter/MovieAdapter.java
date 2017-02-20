package com.example.y1247.popoluar.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.y1247.popoluar.bean.Movie;
import com.example.y1247.popoluar.R;
import com.example.y1247.popoluar.activity.MovieActivity;
import com.example.y1247.popoluar.databinding.ItemLinearMovielistBinding;
import com.example.y1247.popoluar.databinding.ItemMovieBinding;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by y1247 on 2017/1/8.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder>{

    String TAG = "ADAPTER";

    private int listType;

    private Context context;
    private List<Movie> ls;
    private LayoutInflater inflate;

    public MovieAdapter(Context context,int listType){
        this.context = context;
        inflate = LayoutInflater.from(context);
        this.listType = listType;
    }

    public void setList(List<Movie> ls){
        this.ls = ls;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id = 1;
        if(this.listType== 1){
            id = R.layout.item_movie;
        }else{
            id = R.layout.item_linear_movielist;
        }

        ViewDataBinding binding = DataBindingUtil.inflate(
                inflate,
                id,
                parent,
                false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.setWeather(ls.get(position));
        if(listType==1) {
            ((ItemMovieBinding) holder.binding).setMovie(ls.get(position));
        }else{
            ((ItemLinearMovielistBinding) holder.binding).setMovie(ls.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        Movie movie;
        ViewDataBinding binding;

        @BindView(R.id.ll_itemLayout)
        LinearLayout weatherlist_item;
        @BindView(R.id.iv_movieImg)
        ImageView iv_movieImg;

        @OnClick(R.id.ll_itemLayout)
        void OnClick(){
            Intent intent = new Intent(context, MovieActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Movie",this.movie);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }

        MyHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding =  binding;
            ButterKnife.bind(this,itemView);
        }

        void setWeather(Movie movie){
            this.movie = movie;
        }
    }

}
