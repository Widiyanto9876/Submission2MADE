package com.widiyanto.moviecatalogue;

import org.json.JSONObject;

public class MovieItem {
    private String judul;
    private String overview;
    private String date;
    private String poster;

    public MovieItem(JSONObject object){
        try{
            String poster = object.getString("poster_path");
            String judul = object.getString("title");
            String overview = object.getString("overview");
            String date = object.getString("release_date");

            this.poster = poster;
            this.judul = judul;
            this.overview = overview;
            this.date = date;

            //Log.e("judul", judul);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}

