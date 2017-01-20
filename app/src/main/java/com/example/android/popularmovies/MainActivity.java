package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.popularmovies.network.TheMovieDbApi;
import com.example.android.popularmovies.network.Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView  mTestImage;
    private TextView mTestText;
    private ScrollView mScrollView;
    private TextView mTestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestText = (TextView) findViewById(R.id.tv_test_text);
        mTestImage = (ImageView) findViewById(R.id.iv_test_image);
        mScrollView = (ScrollView) findViewById(R.id.sv_data);
        mTestData = (TextView) findViewById(R.id.tv_test_data);

        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .into(mTestImage);


        mTestText.setText(TheMovieDbApi.getPopularMoviesUrl().toString() + "\n\n\n");
        mTestText.append(TheMovieDbApi.getTopRatedMoviesUrl().toString());

        // TODO (1): Get network requets working (look at AsyncTask again)
        URL requestUrl = TheMovieDbApi.getTopRatedMoviesUrl();
        new FetchMovieDataTask().execute(requestUrl);
    }

    public class FetchMovieDataTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            if(urls.length == 0) {
                return null;
            }

            String response = null;
            try {
                response = Utils.getResponseFromHttpUrl(urls[0]);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if(response != "" && response != null) {
                mTestData.setText(response);
            }
        }
    }
}
