package com.samuelhavard.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by samue_000 on 10/31/2015.
 */
public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private MovieData[] mMovieData;

    public MovieAdapter(Context context, MovieData[] movieData) {
        mContext = context;
        mMovieData = movieData;
    }

    @Override
    public int getCount() {
        return mMovieData.length;
    }

    @Override
    public Object getItem(int position) {
        return mMovieData[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ImageView imageView;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.poster_image_item, null);
            holder = new ViewHolder();
            holder.posterImageView = (ImageView) convertView.findViewById(R.id.backgroundImageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        imageView = (ImageView) convertView.findViewById(R.id.backgroundImageView);
        MovieData movie = mMovieData[position];
        Picasso.with(mContext)
                .load(R.string.tmdb_url + movie.getImage())
                .into(imageView);
        holder.posterImageView.setId(R.id.backgroundImageView);


        return convertView;
    }

    private static class ViewHolder {
        ImageView posterImageView;
    }
}

