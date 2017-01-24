package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.network.TheMovieDbApi;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView mTitle = (TextView) findViewById(R.id.tv_movie_title);
        ImageView mPoster = (ImageView) findViewById(R.id.iv_movie_details_poster);
        TextView mOverview = (TextView) findViewById(R.id.tv_overview);
        TextView mVoteAverage = (TextView) findViewById(R.id.tv_vote_average);

        Movie movie = getIntent().getParcelableExtra("movieData");

        Picasso.with(this)
                .load(TheMovieDbApi.getPosterUrlString(movie.posterPath))
                .into(mPoster);

        mTitle.setText(movie.title + " (" + movie.releaseDate + ")");
        mOverview.setText(movie.overview);
        mVoteAverage.setText(String.valueOf(movie.voteAverage));
    }
}
