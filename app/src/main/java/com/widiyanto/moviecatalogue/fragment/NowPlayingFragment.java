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
import com.widiyanto.moviecatalogue.adapter.NowPlayingAdapter;
import com.widiyanto.moviecatalogue.data.NowPlayingData;

import java.util.ArrayList;



public class NowPlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {

    RecyclerView rvNowPlaying;
    NowPlayingAdapter NPAdapter;

    public NowPlayingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        NPAdapter = new NowPlayingAdapter(this.getActivity());
        NPAdapter.notifyDataSetChanged();

        rvNowPlaying = (RecyclerView)view.findViewById(R.id.rv_now_playing);
        rvNowPlaying.setAdapter(NPAdapter);

        getLoaderManager().initLoader(0,null,this);
        return view;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NowPlayingData(this.getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {
        rvNowPlaying.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        NowPlayingAdapter nowPlayingAdapter = new NowPlayingAdapter(this.getActivity());
        nowPlayingAdapter.setData(data);
        rvNowPlaying.setAdapter(nowPlayingAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItem>> loader) {
        NPAdapter.setData(null);

    }
}
