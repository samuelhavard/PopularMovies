package com.samuelhavard.popularmovies;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by samue_000 on 10/30/2015.
 */
public class GridFragment extends Fragment{

    MovieData[] mMovieData;
    GridView mGridView;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Resources resources = getResources();

        mGridView.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.fragment_gridview, mMovieData));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mGridView = (GridView) inflater.inflate(R.layout.activity_main, container, false);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return mGridView;
    }
}
