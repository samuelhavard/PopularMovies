package com.samuelhavard.popularmovies.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.samuelhavard.popularmovies.adapters.MovieAdapter;
import com.samuelhavard.popularmovies.model.MovieData;
import com.samuelhavard.popularmovies.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieActivityDetail extends AppCompatActivity {
    MovieData mMovieData;

    @Bind(R.id.movieDetailTitleTextView) TextView mTitle;
    @Bind(R.id.textViewReleaseYear) TextView mReleaseYear;
    @Bind(R.id.voteAverageTextView) TextView mAverageVote;
    @Bind(R.id.popularityTextView) TextView mPopularity;
    @Bind(R.id.plotTextView) TextView mPlot;
    @Bind(R.id.posterImageView) ImageView mPoster;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        mMovieData = getIntent().getExtras().getParcelable(MovieAdapter.MOVIE);

        mTitle.setText(mMovieData.getTitle());
        mReleaseYear.setText(mMovieData.getDate());
        mAverageVote.setText(mMovieData.getRating());
        mPopularity.setText(mMovieData.getPopularity());
        mPlot.setText(mMovieData.getPlot());

        Picasso.with(this)
                .load(mMovieData.getUrl() + mMovieData.getImage())
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
