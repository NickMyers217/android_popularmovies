package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private TextView mErrorMessage;
    private ProgressBar mLoadingSpinner;

    private enum SortOrder { POPULAR, TOP_RATED }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesGrid = (RecyclerView) findViewById(R.id.rv_movies);
        mMoviesGrid.setHasFixedSize(true);
        mMoviesGrid.setLayoutManager(new GridLayoutManager(this, 2));

        mMovieAdapter = new MovieAdapter();
        mMoviesGrid.setAdapter(mMovieAdapter);

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message_display);

        mLoadingSpinner = (ProgressBar) findViewById(R.id.pb_loading_spinner);

        changeSortOrder(SortOrder.POPULAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.popular:
                changeSortOrder(SortOrder.POPULAR);
                return true;
            case R.id.top_rated:
                changeSortOrder(SortOrder.TOP_RATED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: Look into using onConfigurationChanged to update the adapter and its viewholders for changes from portrait to landscape

    private void changeSortOrder(SortOrder order) {
        URL requestUrl = order == SortOrder.POPULAR
                ? TheMovieDbApi.getPopularMoviesUrl()
                : TheMovieDbApi.getTopRatedMoviesUrl();

        new FetchMovieDataTask().execute(requestUrl);
    }

    private void showMovies() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mMoviesGrid.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mMoviesGrid.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class FetchMovieDataTask extends AsyncTask<URL, Void, ArrayList<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(URL... urls) {
            if(urls.length == 0) {
                return null;
            }

            String response;
            try {
                response = Utils.getResponseFromHttpUrl(urls[0]);

                // TODO: Check the amount of results in the JSON
                JSONArray moviesJson = new JSONObject(response).getJSONArray("results");
                ArrayList<Movie> moviesData = new ArrayList<>();

                for(int i = 0; i < moviesJson.length(); i++) {
                    moviesData.add(new Movie(moviesJson.getJSONObject(i)));
                }

                return moviesData;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> moviesData) {
            super.onPostExecute(moviesData);
            mLoadingSpinner.setVisibility(View.INVISIBLE);

            if(moviesData == null) {
                showErrorMessage();
                Log.e(this.getClass().getSimpleName(), "Network error, error message shown.");
                // TODO: Implement a try again button
                return;
            }

            showMovies();
            mMovieAdapter.setMovies(moviesData);
            mMovieAdapter.setClickListener(new MovieAdapter.MovieClickListener(){
                @Override
                public void onItemClick(Movie movie, View v) {
                    Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
                    detailsIntent.putExtra("movieData", movie);
                    startActivity(detailsIntent);
                }
            });
        }
    }
}
