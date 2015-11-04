package com.samuelhavard.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
/**
 *Movie adapter accepts an array of MovieData objects and queries the API with each objects
 * poster path.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    public static final String MOVIE = "MOVIE";

    private Context mContext;
    private MovieData[] mMovieData;
    private MovieData mMovie;

    public MovieAdapter(Context context, MovieData[] movieData) {
        mContext = context;
        mMovieData = movieData;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poster_image_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovieData[position]);
    }

    @Override
    public int getItemCount() {
        return mMovieData.length;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public ImageView mBackground;
        public TextView mTitle;

        public MovieViewHolder (View itemView) {
            super(itemView);

            mBackground = (ImageView) itemView.findViewById(R.id.backgroundImageView);
            mTitle = (TextView) itemView.findViewById(R.id.titleTextView);

            itemView.setOnClickListener(this);
        }

        public void bindMovie(MovieData movieData) {
            mTitle.setText(movieData.getTitle());
            Picasso.with(mContext)
                    .load("http://image.tmdb.org/t/p/w185/" + movieData.getImage())
                    .into(mBackground);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, MovieActivityDetail.class);
            int pos = getAdapterPosition();
            intent.putExtra(MOVIE, mMovieData[pos]);
            mContext.startActivity(intent);
        }
    }
}

