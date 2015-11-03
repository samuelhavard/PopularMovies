package com.samuelhavard.popularmovies;

import android.content.Context;
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
        String url = "http://image.tmdb.org/t/p/w185/" + mMovieData[position].getImage();

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.poster_image_item, null);
            holder = new ViewHolder();
            holder.posterView = (ImageView) convertView.findViewById(R.id.backgroundImageView);
            holder.title = (TextView) convertView.findViewById(R.id.titleTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext)
                .load(url)
                .into(holder.posterView);
        holder.title.setText(mMovieData[position].getTitle());

        return convertView;
    }
    private static class ViewHolder {
        ImageView posterView;
        TextView title;
    }
}

