package com.samuelhavard.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivityDetail extends AppCompatActivity {
    MovieData mMovieData;

    TextView mTitle;
    TextView mReleaseYear;
    TextView mAverageVote;
    TextView mPopularity;
    TextView mPlot;
    ImageView mPoster;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        url = "http://image.tmdb.org/t/p/w185/";

        mMovieData = getIntent().getExtras().getParcelable(MovieAdapter.MOVIE);

        mTitle = (TextView) findViewById(R.id.movieDetailTitleTextView);
        mReleaseYear = (TextView) findViewById(R.id.textViewReleaseYear);
        mAverageVote = (TextView) findViewById(R.id.voteAverageTextView);
        mPopularity = (TextView) findViewById(R.id.popularityTextView);
        mPlot = (TextView) findViewById(R.id.plotTextView);
        mPoster = (ImageView) findViewById(R.id.posterImageView);

        mTitle.setText(mMovieData.getTitle());
        mReleaseYear.setText(mMovieData.getDate());
        mAverageVote.setText(mMovieData.getRating());
        mPopularity.setText(mMovieData.getPopularity());
        mPlot.setText(mMovieData.getPlot());

        Picasso.with(this)
                .load(url + mMovieData.getImage())
                .into(mPoster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_activity_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
