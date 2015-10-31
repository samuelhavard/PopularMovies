package com.samuelhavard.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

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
 * GetMovieData makes the call to the API and returns an array of MovieData objects.
 */
public class GetMovieData {

    final static String TAG = GetMovieData.class.getSimpleName();

    private Activity mActivity;
    MovieData[] mMovieData;

    public GetMovieData(Activity activity) {
        mActivity = activity;
    }
    /**
     * Builds the URI and makes teh network call to the "The Movie Database" API
     * Performs calls to ensure the network is available and passes the JSON data
     * to the getMovieData method to be parsed into MovieData objects.
     */
    public void getMovies() {

        String sort = "popularity.desc";

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
                     mActivity.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             Log.d(TAG, "onResponse run()");
                         }
                     });
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            Log.d(TAG, jsonData);
                            getMovieData(jsonData);

                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException | JSONException e) {
                        Log.e(TAG, "Exception caught", e);
                    }
                }
            });

        } else {
            Toast.makeText(mActivity, R.string.network_is_unavailable, Toast.LENGTH_LONG).show();
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
                mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
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
        dialog.show(mActivity.getFragmentManager(), "error_dialog");
    }

    /**
     * Accepts the JSON data from the network calls response and fills an array of
     * MovieData objects.
     *
     * @param jsonData is a JSON string from the database to be parsed into MovieData objects
     * @return MovieData[] is an array of MovieData objects
     * @throws JSONException
     */
    public void getMovieData(String jsonData) throws JSONException {
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
        mMovieData = movies;
    }
}
