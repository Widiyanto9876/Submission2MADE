package com.widiyanto.moviecatalogue.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.widiyanto.moviecatalogue.R;



public class DetailFragment extends Fragment {
    private ImageView imgPoster;
    private TextView tvJudul,tvKeterangan,tvDate;
    public static String PosterUrl = "Poster";
    public static String Judul = "judul";
    public static String Keterangan = "keterangan";
    public static String Date = "date";

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        imgPoster = (ImageView)view.findViewById(R.id.img_photo);
        tvKeterangan = (TextView)view.findViewById(R.id.tv_overview);
        tvDate = (TextView)view.findViewById(R.id.tv_date);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        String imgUrl = getArguments().getString(PosterUrl);
        String judul = getArguments().getString(Judul);
        String keterangan = getArguments().getString(Keterangan);
        String date = getArguments().getString(Date);

        if (saveInstanceState == null) {
            Glide.with(this)
                    .load(imgUrl)
                    .crossFade()
                    .into(imgPoster);
            tvKeterangan.setText(keterangan);
            tvDate.setText(date);
        }
    }



}
