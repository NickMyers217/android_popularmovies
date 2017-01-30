package com.example.android.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.views.MovieViewHolder;

import java.util.ArrayList;


/**
 * An implementation of RecyclerView.Adapter for the app's RecyclerView
 *
 * It contains an ArrayList of Movies that are bound to ViewHolders.
 * The VieHolders form a grid layout of Movie posters.
 * It also implements a MovieClickListener for each ViewHolder.
 */
class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private MovieClickListener mClickListener;

    interface MovieClickListener {
        void onItemClick(Movie movie, View v);
    }

    MovieAdapter() {}

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.movie_item, parent, false);

        // TODO: make this work on all screen sizes and orientations
        // Set the height of the view holder based on the height of the screen
        view.setMinimumWidth(parent.getWidth() / 2);
        view.setMinimumHeight(parent.getHeight() / 2);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        if(mMovies.size() == 0 || mMovies == null) {
            return;
        }

        final Movie movie = mMovies.get(position);
        holder.bind(movie);
        holder.getMoviePoster().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(movie, v);
            }
        });
    }

    @Override
    public int getItemCount() { return mMovies.size(); }

    void setClickListener(MovieClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    void setMovies(ArrayList<Movie> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}
