package com.example.android.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

// TODO: Look into some kind of JSON deserialization/mapping library to cut down the code in here
public class Movie implements Parcelable {
    public String title;
    public String posterPath;

    public Movie(JSONObject movieJson) {
        // TODO: Implement the rest of the fields as needed for the details activity
        try {
            title = movieJson.getString("title");
            posterPath = movieJson.getString("poster_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
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
    }
}
