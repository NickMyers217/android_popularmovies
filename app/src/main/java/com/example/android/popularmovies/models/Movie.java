package com.example.android.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents the data model for a movie in the app.
 */
public class Movie implements Parcelable {
    public String title;
    public String posterPath;
    public String overview;
    public float voteAverage;
    public String releaseDate;

    /**
     * A constructor that builds a Movie object from a JSONObject.
     *
     * @param movieJson A JSONObject that contains all the necessary fields for the movie.
     */
    public Movie(JSONObject movieJson) {
        try {
            title = movieJson.getString("original_title");
            posterPath = movieJson.getString("poster_path");
            overview = movieJson.getString("overview");
            voteAverage = Float.parseFloat(movieJson.getString("vote_average"));
            releaseDate = movieJson.getString("release_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * A constructor that builds a movie from a Parcel object.
     *
     * @param in A Parcel object that contains the correct fields for the Movie.
     */
    private Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        voteAverage = Float.parseFloat(in.readString());
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(String.valueOf(voteAverage));
        dest.writeString(releaseDate);
    }
}
