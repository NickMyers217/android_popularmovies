package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO (1): get the image library working
        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .into((ImageView) findViewById(R.id.iv_test_image));
        // TODO (2): get the api url's working
        // TODO (3): get network requests working
    }
}
