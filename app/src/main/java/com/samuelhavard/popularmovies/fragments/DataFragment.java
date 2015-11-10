package com.samuelhavard.popularmovies.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samuelhavard.popularmovies.BuildConfig;
import com.samuelhavard.popularmovies.R;
import com.samuelhavard.popularmovies.activities.MovieActivity;
import com.samuelhavard.popularmovies.activities.SettingsActivity;
import com.samuelhavard.popularmovies.model.MovieData;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by samue_000 on 11/7/2015.
 */
public class DataFragment extends Fragment {

    final static String TAG = DataFragment.class.getSimpleName();
    public static final String MOVIE_DATA = "MOVIE_DATA";
    SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferences;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getMovies();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMovies();
    }

    /**
     * Builds the URI and makes teh network call to the "The Movie Database" API
     * Performs calls to ensure the network is available and passes the JSON data
     * to the showMovieData method to be parsed into MovieData objects.
     */
    private void getMovies() {

        String sort = "";

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());

        String sortStyle = sharedPreferences.getString(
                getString(R.string.pref_sort_key),
                getString(R.string.pref_default_search));

        if (sortStyle.equals("Popularity")) {
            sort = getString(R.string.sort_popularity);
        } else if (sortStyle.equals("Voter Average")) {
            sort = getString(R.string.sort_vote);
        }

        final String BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
        final String SORT_BY_PARAM = "sort_by";
        final String API_PARAM = "api_key";

        Uri uriBuilder = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(SORT_BY_PARAM, sort)
                .appendQueryParameter(API_PARAM, BuildConfig.TMDB_API_KEY)
                .build();

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(uriBuilder.toString())
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Log.d(TAG, "onResponse run()");
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            //Log.d(TAG, jsonData);
                            showMovieData(jsonData);

                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException | JSONException e) {
                        Log.e(TAG, "Exception caught", e);
                    }
                }
            });

        } else {
            Toast.makeText(getActivity(), R.string.network_is_unavailable, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Uses the connectivity manager to ensure the network is available
     * before any network calls are attempted.
     *
     * @return boolean
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    /**
     * Alerts the user to an error that has occurred.
     */
    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        dialog.show(fragmentManager, "error_dialog");
    }

    /**
     * Accepts the JSON data from the network calls response and fills an array of
     * MovieData objects.
     *
     * @param jsonData is a JSON string from the database to be parsed into MovieData objects
     * @throws JSONException
     */
    private void showMovieData(String jsonData) throws JSONException {
        JSONObject moviesJsonStr = new JSONObject(jsonData);
        JSONArray moviesArray = moviesJsonStr.getJSONArray("results");

        MovieData[] movies = new MovieData[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movie = moviesArray.getJSONObject(i);
            MovieData movieData = new MovieData();

            movieData.setTitle(movie.getString("original_title"));
            movieData.setRating(movie.getString("vote_average"));
            movieData.setPopularity(movie.getString("popularity"));
            movieData.setPlot(movie.getString("overview"));
            movieData.setImage(movie.getString("poster_path"));
            movieData.setDate(movie.getString("release_date"));

            movies[i] = movieData;
        }
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra(MOVIE_DATA, movies);
        startActivity(intent);
    }
}

