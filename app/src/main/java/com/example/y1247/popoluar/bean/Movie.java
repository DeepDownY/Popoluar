package com.example.y1247.popoluar.bean;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.example.y1247.popoluar.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by y1247 on 2017/1/8.
 */

public class Movie implements Parcelable{
    static String beginUrl = "http://image.tmdb.org/t/p/w185";

    private String title;
    private String overview;
    private String vote_average;
    private String release_date;
    private String poster_path;
    private String backdrop_path;

    @BindingAdapter({"imageUrl"})
    public static void setImage(ImageView view, String imageUrl){

        Picasso.with(view.getContext())
                .load(beginUrl+imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    public Movie() {
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(vote_average);
        parcel.writeString(release_date);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);

    }

    public Movie(Parcel source){
        title = source.readString();
        overview = source.readString();
        vote_average = source.readString();
        release_date = source.readString();
        poster_path = source.readString();
        backdrop_path = source.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {


        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }


        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }
    };
}
