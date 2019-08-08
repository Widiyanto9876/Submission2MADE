package com.widiyanto.moviecatalogue.data;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.widiyanto.moviecatalogue.BuildConfig;
import com.widiyanto.moviecatalogue.MovieItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class UpcomingData extends AsyncTaskLoader<ArrayList<MovieItem>> {
    private ArrayList<MovieItem> mData;
    private boolean mHasResult = false;
    private MovieItem upcomingItems;

    public UpcomingData(final Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItem> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = BuildConfig.API_KEY;

    @Override
    public ArrayList<MovieItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItem> upcomingItemses = new ArrayList<>();

        String language = "en-US";
        if (Locale.getDefault().getDisplayLanguage().contains("English")) {
            language = "en-US";
        }
        if (Locale.getDefault().getDisplayLanguage().contains("Indonesia")) {
            language = "id-ID";
        }
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language="+language+"";

        client.get(String.valueOf(url), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject catalugue = list.getJSONObject(i);
                        upcomingItems = new MovieItem(catalugue);
                        upcomingItemses.add(upcomingItems);
                        //Log.e("UCData", String.valueOf(catalugue));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return upcomingItemses;
    }

    private void onReleaseResources(ArrayList<MovieItem> data) {
    }
}

