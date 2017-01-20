package com.example.android.popularmovies.network;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
    /**
     * Converts a given uri to a url if possible
     *
     * @param uri the input uri
     * @return the resulting url address
     */
    public static URL convertUriToUrl(Uri uri) {
        URL result = null;
        try {
            result = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //TODO: Write a method to get an HTTP response from the network
}
