package com.example.android.popularmovies;

import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.network.TheMovieDbApi;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private TextView mTestText;
    private ImageView  mTestImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestText = (TextView) findViewById(R.id.tv_test_text);
        mTestImage = (ImageView) findViewById(R.id.iv_test_image);

        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .into(mTestImage);


        mTestText.setText(TheMovieDbApi.getPopularMoviesUrl().toString() + "\n\n\n");
        mTestText.append(TheMovieDbApi.getTopRatedMoviesUrl().toString());
    }
}
