package com.samuelhavard.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by samue_000 on 10/31/2015.
 */
public class MovieAdapter extends ArrayAdapter {

    private Context mContext;
    private MovieData[] mMovieData;
    private LayoutInflater mLayoutInflater;

    public MovieAdapter(Context context, MovieData[] movieData) {
        super(context, R.layout.poster_image_item);

        mContext = context;
        mMovieData = movieData;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.poster_image_item, parent, false);
        } else {
            Picasso.with(mContext)
                    .load(R.string.tmdb_url + mMovieData[position].getImage())
                    .into((ImageView) convertView);
        }
        return convertView;
    }

}

