package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.popularmovies.models.Movie;

public class DetailsActivity extends AppCompatActivity {

    private TextView mTestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mTestText = (TextView) findViewById(R.id.test_data);
        Movie movie= getIntent().getParcelableExtra("movieData");
        mTestText.setText(movie.title);
    }
}
