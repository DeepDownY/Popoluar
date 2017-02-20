package com.example.y1247.popoluar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y1247.popoluar.bean.Movie;
import com.example.y1247.popoluar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {
    @BindView(R.id.iv_movieDetal)
    ImageView iv_movieDetal;
    @BindView(R.id.tv_movieDate)
    TextView tv_movieDate;
    @BindView(R.id.tv_movieRate)
    TextView tv_movieRate;
    @BindView(R.id.tv_movieOverView)
    TextView tv_movieOverView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);

        Log.i("", "onCreate: ");
        Intent intent = getIntent();

        Movie movie = intent.getParcelableExtra("Movie");

        Movie.setImage(iv_movieDetal,movie.getPoster_path());
        tv_movieDate.setText(movie.getRelease_date());
        tv_movieOverView.setText(movie.getOverview());
        tv_movieRate.setText(movie.getVote_average());
        toolbar.setTitle(movie.getTitle());

        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

}
