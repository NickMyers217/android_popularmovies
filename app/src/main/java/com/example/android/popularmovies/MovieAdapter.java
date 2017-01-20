package com.example.android.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private int mNumberOfItems;
    // TODO: Refactor this code to some kind of data model class
    private JSONArray mMovieList;

    public MovieAdapter(int numberOfItems) {
        mNumberOfItems = numberOfItems;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() { return mNumberOfItems; }

    public void setData(String data) {
        // TODO: Refactor this code to some kind of data model class
        try {
            mMovieList = new JSONObject(data).getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView mTestText;
        private ImageView mMoviePoster;

        MovieViewHolder(View itemView) {
            super(itemView);
            mTestText = (TextView) itemView.findViewById(R.id.tv_test_data);
            mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
        }

        void bind(int position) {
            if(mMovieList == null) {
                return;
            }

            // TODO: Refactor this code to some kind of data model class
            JSONObject movie;
            try {
                movie = mMovieList.getJSONObject(position);
                String posterPath = movie.getString("poster_path");
                String posterUrlString = "https://image.tmdb.org/t/p/w185/" + posterPath;

                Picasso.with(this.itemView.getContext())
                        .load(posterUrlString)
                        .into(mMoviePoster);

                mTestText.setText(posterUrlString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
