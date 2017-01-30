package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.network.TheMovieDbApi;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.tv_movie_title) TextView mTitle;
    @BindView(R.id.iv_movie_details_poster) ImageView mPoster;
    @BindView(R.id.tv_overview) TextView mOverview;
    @BindView(R.id.tv_vote_average) TextView mVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Use ButterKnife for view binding
        ButterKnife.bind(this);

        Movie movie = getIntent().getParcelableExtra("movieData");

        Picasso.with(this)
                .load(TheMovieDbApi.getPosterUrlString(movie.posterPath))
                .into(mPoster);

        mTitle.setText(movie.title + " (" + movie.releaseDate + ")");
        mOverview.setText(movie.overview);
        mVoteAverage.setText(String.valueOf(movie.voteAverage));
    }
}
