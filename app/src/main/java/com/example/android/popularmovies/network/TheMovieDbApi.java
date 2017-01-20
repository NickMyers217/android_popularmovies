package com.example.android.popularmovies.network;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

// TODO: Make this class implement a MovieApi interface in the future to allow for extensibility??
public class TheMovieDbApi {
    // TODO: Remember to replace this string your real API key
    private static final String API_KEY = "NOT_MY_REAL_KEY_YET";
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String POPULAR_PATH = "popular";
    private static final String TOP_RATED_PATH = "top_rated";

    /**
     * Constructs the correct theMovieDb API endpoint for the given string path
     *
     * @param path String name of the api endpoint you wish to build the url for
     * @return the correctly formed url for that api endpoint
     */
    private static URL constructEndpointUrl(String path) {
        Uri uri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(path)
                .appendQueryParameter("api_key", API_KEY)
                .build();

        return Utils.convertUriToUrl(uri);
    }

    /**
     * Builds and returns the url for the /popular endpoint
     *
     * @return the url address
     */
    public static URL getPopularMoviesUrl() {
        return constructEndpointUrl(POPULAR_PATH);
    }

    /**
     * Builds and returns the url for the /top_rated endpoint
     *
     * @return the url address
     */
    public static URL getTopRatedMoviesUrl() {
        return constructEndpointUrl(TOP_RATED_PATH);
    }
}
