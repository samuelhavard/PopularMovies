package com.samuelhavard.popularmovies;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.Arrays;

import butterknife.Bind;

/**
 * Created by samue_000 on 10/31/2015.
 */
public class MovieActivity extends Activity {
    private MovieData[] mMovieData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.MOVIE_DATA);
        mMovieData = Arrays.copyOf(parcelables, parcelables.length, MovieData[].class);

        GridView gridView = (GridView) findViewById(R.id.mainActivity_GridView);

       // MovieAdapter adapter = new MovieAdapter(this, mMovieData);
        gridView.setAdapter(new MovieAdapter(this, mMovieData));
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//    }
}
