package com.example.android.popularmovies.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.network.TheMovieDbApi;
import com.squareup.picasso.Picasso;

/**
 * An implementation of ViewHolder to be used for the RecyclerView's grid items.
 *
 * It just shows a Movie's poster image.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView mMoviePoster;

    public MovieViewHolder(View itemView) {
        super(itemView);
        mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
    }

    /**
     * Binds data from a Movie to the ViewHolder.
     *
     * @param movie A Movie that you want to populate ViewHolder with.
     */
    public void bind(Movie movie) {
        String posterUrlString = TheMovieDbApi.getPosterUrlString(movie.posterPath);

        // TODO: Make sure this supports all the apis
        Picasso.with(itemView.getContext())
                .load(posterUrlString)
                .resize(itemView.getMinimumWidth(), itemView.getMinimumHeight())
                .into(mMoviePoster);
    }

    public ImageView getMoviePoster() {
        return mMoviePoster;
    }
}
