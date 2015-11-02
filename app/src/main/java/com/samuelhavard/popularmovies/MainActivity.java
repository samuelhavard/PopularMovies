package com.samuelhavard.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    MovieData[] mMovieData;


    final static String TAG = MainActivity.class.getSimpleName();
    public static final String MOVIE_DATA = "MOVIE_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * Builds the URI and makes teh network call to the "The Movie Database" API
     * Performs calls to ensure the network is available and passes the JSON data
     * to the getMovieData method to be parsed into MovieData objects.
     */
    private void getMovies() {

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
                    runOnUiThread(new Runnable() {
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
            Toast.makeText(this, R.string.network_is_unavailable, Toast.LENGTH_LONG).show();
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
                getSystemService(Context.CONNECTIVITY_SERVICE);
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
        dialog.show(getFragmentManager(), "error_dialog");
    }

    /**
     * Accepts the JSON data from the network calls response and fills an array of
     * MovieData objects.
     *
     * @param jsonData is a JSON string from the database to be parsed into MovieData objects
     * @return MovieData[] is an array of MovieData objects
     * @throws JSONException
     */
    private void getMovieData(String jsonData) throws JSONException {
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
        //return movies;
        mMovieData = movies;
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(MOVIE_DATA, mMovieData);
        startActivity(intent);
    }
//    public void startMovieActivity(MovieData[] mMovieData) {
//        Intent intent = new Intent(this, MovieActivity.class);
//        intent.putExtra(MOVIE_DATA, mMovieData);
//        startActivity(intent);
//    }
}
