package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.network.TheMovieDbApi;
import com.example.android.popularmovies.network.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

// TODO: Documentation
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
            if(response == null) {
                return;
            }

            // TODO: Check the amount of results in the JSON
            JSONArray moviesJson;
            ArrayList<Movie> moviesData = new ArrayList<>();
            try {
                moviesJson = new JSONObject(response).getJSONArray("results");

                for(int i = 0; i < moviesJson.length(); i++) {
                    moviesData.add(new Movie(moviesJson.getJSONObject(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mMovieAdapter = new MovieAdapter(moviesData, new MovieAdapter.MovieClickListener() {
                @Override
                public void onItemClick(Movie movie, View v) {
                    Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
                    detailsIntent.putExtra("movieData", movie);
                    startActivity(detailsIntent);
                }
            });
            mMoviesGrid.setAdapter(mMovieAdapter);

            // TODO: Implement an error message and a refresh button if things go wrong
        }
    }
}
