package com.samuelhavard.popularmovies;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.GridView;

import java.util.Arrays;


/**
 *
 */
public class MovieActivity extends Activity {
    private MovieData[] mMovieData;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mGridView = (GridView)findViewById(R.id.movie_grid);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.MOVIE_DATA);
        mMovieData = Arrays.copyOf(parcelables, parcelables.length, MovieData[].class);

        MovieAdapter movieAdapter = new MovieAdapter(this, mMovieData);
        mGridView.setAdapter(movieAdapter);
    }
}
