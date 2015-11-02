package com.samuelhavard.popularmovies;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.Arrays;


/**
 * Created by samue_000 on 10/31/2015.
 */
public class MovieActivity extends ListActivity {
    private MovieData[] mMovieData;
    //GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

       // mGridView = (GridView) findViewById(R.id.mainActivity_GridView);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.MOVIE_DATA);
        mMovieData = Arrays.copyOf(parcelables, parcelables.length, MovieData[].class);

       // mGridView.setAdapter(new MovieAdapter(this, mMovieData));
        MovieAdapter movieAdapter = new MovieAdapter(this, mMovieData);
        setListAdapter(movieAdapter);
    }
}
