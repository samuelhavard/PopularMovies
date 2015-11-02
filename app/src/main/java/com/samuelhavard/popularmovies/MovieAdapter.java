package com.samuelhavard.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


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
        //ImageView view = (ImageView) convertView;
        ViewHolder holder;
        String url = "http://image.tmdb.org/t/p/w185/" + mMovieData[position].getImage();

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.poster_image_item, null);
            holder = new ViewHolder();

            holder.posterView = (ImageView) convertView.findViewById(R.id.backgroundImageView);
            //Picasso.with(mContext).load(url).fit().tag(mContext).into(holder.posterView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext)
                .load(url)
                .into(holder.posterView);

        return convertView;
    }
    private static class ViewHolder {
        ImageView posterView;
    }

//        @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View row = convertView;
//        ViewHolder holder;
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.poster_image_item, null);
//            holder = new ViewHolder();
//            holder.posterView = (ImageView) convertView.findViewById(R.id.backgroundImageView);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) row.getTag();
//        }
//
//        Picasso.with(mContext)
//                .load(R.string.tmdb_url + mMovieData[position].getImage())
//                .into(holder.posterView);
//        return row;
//    }
//    private static class ViewHolder {
//        ImageView posterView;
//    }

}

