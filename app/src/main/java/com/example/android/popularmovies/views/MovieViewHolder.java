package com.example.android.popularmovies.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.network.TheMovieDbApi;
import com.squareup.picasso.Picasso;

// TODO: Documentation
public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView mMoviePoster;

    public MovieViewHolder(View itemView) {
        super(itemView);
        mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
    }

    public void bind(Movie movie) {
        String posterUrlString = TheMovieDbApi.getPosterUrlString(movie.posterPath);

        // TODO: Evaluate if this is the best way to size the images responsively
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
