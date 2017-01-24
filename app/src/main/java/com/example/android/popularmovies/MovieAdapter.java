package com.example.android.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.network.TheMovieDbApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// TODO: Documentation
class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies;
    private MovieClickListener mClickListener;

    interface MovieClickListener {
        void onItemClick(Movie movie, View v);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView mMoviePoster;

        MovieViewHolder(View itemView) {
            super(itemView);
            mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
        }

        void bind(Movie movie) {
            String posterUrlString = TheMovieDbApi.getPosterUrlString(movie.posterPath);

            // TODO: Evaluate if this is the best way to size the images responsively
            Picasso.with(this.itemView.getContext())
                    .load(posterUrlString)
                    .resize(itemView.getMinimumWidth(), itemView.getMinimumHeight())
                    .into(mMoviePoster);
        }
    }

    MovieAdapter(ArrayList<Movie> data, MovieClickListener clickListener) {
        mMovies = data;
        mClickListener = clickListener;
    }

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
        holder.mMoviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(movie, v);
            }
        });
    }

    @Override
    public int getItemCount() { return mMovies.size(); }
}
