package com.widiyanto.moviecatalogue.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.widiyanto.moviecatalogue.MovieItem;
import com.widiyanto.moviecatalogue.R;
import com.widiyanto.moviecatalogue.adapter.UpcomingAdapter;
import com.widiyanto.moviecatalogue.data.UpcomingData;

import java.util.ArrayList;

public class UpcomingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {

    RecyclerView rvUpcoming;
    UpcomingAdapter UCAdapter;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        UCAdapter = new UpcomingAdapter(this.getActivity());
        UCAdapter.notifyDataSetChanged();

        rvUpcoming = (RecyclerView)view.findViewById(R.id.rv_upcoming);
        rvUpcoming.setAdapter(UCAdapter);

        getLoaderManager().initLoader(0,null,this);
        return view;
    }



    @NonNull
    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, @Nullable Bundle args) {
        return new UpcomingData(this.getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {

        rvUpcoming.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        UpcomingAdapter upcomingAdapter = new UpcomingAdapter(this.getActivity());
        upcomingAdapter.setData(data);
        rvUpcoming.setAdapter(upcomingAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItem>> loader) {

        UCAdapter.setData(null);
    }
}
