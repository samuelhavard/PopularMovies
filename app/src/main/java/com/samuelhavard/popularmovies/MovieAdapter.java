package com.samuelhavard.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by samue_000 on 10/31/2015.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private MovieData[] mMovieData;

    public MovieAdapter(Context context, MovieData[] movieData) {
        mContext = context;
        mMovieData = movieData;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder movieViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public ImageView mPoster;

        public MovieViewHolder(View itemView) {
            super(itemView);

            mPoster = (ImageView) itemView.findViewById(R.id.backgroundImageView);
            itemView.setOnClickListener(this);
        }

        public void bindMovie(MovieData movie) {
            Picasso.with(mContext).load(R.string.tmdb_url + movie.getImage()).into(mPoster);
            //mPoster.setImageResource();
        }

        @Override
        public void onClick(View v) {

        }
    }
}

