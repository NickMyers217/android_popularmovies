package com.example.android.popularmovies.network;

import android.net.Uri;

import java.net.URL;

public class TheMovieDbApi {
    // TODO: Remember to replace this string your real API key from the themoviedb.org
    private static final String API_KEY = "YOUR_KEY_GOES_HERE";

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185/";
    private static final String POPULAR_PATH = "popular";
    private static final String TOP_RATED_PATH = "top_rated";

    /**
     * Constructs the correct theMovieDb API endpoint for the given string path.
     *
     * @param path String name of the api endpoint you wish to build the url for.
     * @return The correctly formed url for that api endpoint.
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
     * Builds and returns the url for the /popular endpoint.
     *
     * @return The url address.
     */
    public static URL getPopularMoviesUrl() {
        return constructEndpointUrl(POPULAR_PATH);
    }

    /**
     * Builds and returns the url for the /top_rated endpoint.
     *
     * @return The url address.
     */
    public static URL getTopRatedMoviesUrl() {
        return constructEndpointUrl(TOP_RATED_PATH);
    }

    /**
     * Returns the full URL for the given posterPath as a String
     *
     * @param posterPath String representing the path of the desired poster
     * @return The URL for the poster as a string
     */
    public static String getPosterUrlString(String posterPath) {
        return POSTER_BASE_URL + posterPath;
    }
}
