package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.popularmovies.network.TheMovieDbApi;
import com.example.android.popularmovies.network.Utils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMoviesGrid;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesGrid = (RecyclerView) findViewById(R.id.rv_movies);
        mMoviesGrid.setHasFixedSize(true);
        mMoviesGrid.setLayoutManager(new GridLayoutManager(this, 2));

        URL requestUrl = TheMovieDbApi.getPopularMoviesUrl();
        new FetchMovieDataTask().execute(requestUrl);
    }

    public class FetchMovieDataTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // TODO: Implement a loading spinner
        }

        @Override
        protected String doInBackground(URL... urls) {
            if(urls.length == 0) {
                return null;
            }

            String response;
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

            // TODO: Implement a loading spinner
            if(response != null) {
                mMovieAdapter = new MovieAdapter(TheMovieDbApi.MOVIES_PER_PAGE);
                mMovieAdapter.setData(response);
                mMoviesGrid.setAdapter(mMovieAdapter);
            }

            // TODO: Implement an error message and a refresh button if things go wrong
        }
    }
}
