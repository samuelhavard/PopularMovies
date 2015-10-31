package com.samuelhavard.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;


/**
 * Created by samue_000 on 10/31/2015.
 */
public class MovieActivity extends Activity {
    private MovieData[] mMovieData;

    RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.MOVIE_DATA);
        mMovieData = Arrays.copyOf(parcelables, parcelables.length, MovieData[].class);

       // GridView gridView = (GridView) findViewById(R.id.mainActivity_GridView);

        MovieAdapter adapter = new MovieAdapter(this, mMovieData);
        //gridView.setAdapter(new MovieAdapter(this, mMovieData));
        mRecyclerView.setAdapter(adapter);

        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//    }
}
