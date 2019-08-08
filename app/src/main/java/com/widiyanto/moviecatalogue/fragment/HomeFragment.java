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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.widiyanto.moviecatalogue.MovieItem;
import com.widiyanto.moviecatalogue.R;
import com.widiyanto.moviecatalogue.adapter.MainAdapter;
import com.widiyanto.moviecatalogue.data.MainData;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {

    RecyclerView rvHome;
    MainAdapter MainAdapter;
    EditText edtSearch;
    Button btnSearch;
    static final String EXTRAS_CARI = "CARI";


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        MainAdapter = new MainAdapter(this.getActivity());
        MainAdapter.notifyDataSetChanged();

        rvHome = (RecyclerView)view.findViewById(R.id.rv_search);
        rvHome.setAdapter(MainAdapter);

        edtSearch = (EditText)view.findViewById(R.id.edt_search);
        btnSearch = (Button)view.findViewById(R.id.btn_Search);
        btnSearch.setOnClickListener(myListener);

        String judul = edtSearch.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_CARI,judul);
        getLoaderManager().initLoader(0,bundle,this);

        String title = String.format(getResources().getString(R.string.home));

        return view;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, @Nullable Bundle args) {
        String cariMovie = "";
        if (args != null){
            cariMovie = args.getString(EXTRAS_CARI);
        }
        return new MainData(this.getActivity(),cariMovie);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {
        rvHome.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        MainAdapter mainAdapter = new MainAdapter(this.getActivity());
        mainAdapter.setData(data);
        rvHome.setAdapter(mainAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItem>> loader) {
        MainAdapter.setData(null);

    }
    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_Search:
                    MainAdapter.clearData();
                    String judul = edtSearch.getText().toString();

                    if (TextUtils.isEmpty(judul))return;

                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRAS_CARI,judul);
                    getLoaderManager().restartLoader(0, bundle,HomeFragment.this);
                    break;
            }
        }
    };
}
