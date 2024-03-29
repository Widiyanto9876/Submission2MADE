package com.widiyanto.moviecatalogue.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.widiyanto.moviecatalogue.CustomOnItemClickListener;
import com.widiyanto.moviecatalogue.MovieItem;
import com.widiyanto.moviecatalogue.R;
import com.widiyanto.moviecatalogue.fragment.DetailFragment;

import java.util.ArrayList;

public class UpcomingAdapter extends
        RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>{
    private Context context;
    private ArrayList<MovieItem> mData;

    public UpcomingAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<MovieItem> items){
        this.mData = items;
        notifyDataSetChanged();
    }

    public MovieItem getItem(int position) {
        return mData.get(position);
    }

    @Override
    public UpcomingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UpcomingViewHolder holder, int position) {
        String imgUrl = "http://image.tmdb.org/t/p/w185"+mData.get(position).getPoster();

        Glide.with(context)
                .load(imgUrl)
                .override(350, 550)
                .crossFade()
                .into(holder.imgPhoto);

        holder.tvName.setText(mData.get(position).getJudul());
        holder.tvRemarks.setText(mData.get(position).getDate());

        holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                getItem(position);
                String imgUrl = "http://image.tmdb.org/t/p/w342"+getItem(position).getPoster();
                String judul = getItem(position).getJudul();
                String keterangan = getItem(position).getOverview();
                String date = getItem(position).getDate();
                DetailFragment detailFragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString(DetailFragment.PosterUrl, imgUrl);
                bundle.putString(DetailFragment.Judul, judul);
                bundle.putString(DetailFragment.Keterangan, keterangan);
                bundle.putString(DetailFragment.Date, date);
                detailFragment.setArguments(bundle);
                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main,detailFragment)
                        .addToBackStack(null)
                        .commit();

            }
        }));
    }

    @Override
    public int getItemCount() {
        Log.e("getItem", String.valueOf(mData));
        if (mData == null) return 0;
        return mData.size();
    }

    class UpcomingViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName,tvRemarks;
        Button btnDetail;

        public UpcomingViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tv_title);
            tvRemarks = (TextView)itemView.findViewById(R.id.tv_overview);
            imgPhoto = (ImageView)itemView.findViewById(R.id.img_photo);
            btnDetail = (Button)itemView.findViewById(R.id.btn_detail);
        }
    }
}
