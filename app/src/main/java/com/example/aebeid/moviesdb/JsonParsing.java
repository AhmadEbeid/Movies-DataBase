package com.example.aebeid.moviesdb;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.aebeid.moviesdb.MainActivity.MostPopularSortedArrays;
import static com.example.aebeid.moviesdb.MainActivity.TopRatedSortedArrays;

public class JsonParsing extends AsyncTask<String, Void, Void> {
    private Context mContext;

    public JsonParsing(Context c) {
        mContext = c;
    }

    @Override
    protected Void doInBackground(String... json) {

        try {
            JSONObject jsonObject = new JSONObject(json[0]);
            JSONArray resultArray = (JSONArray) jsonObject.get("results");

            //JSONArray resultArray // = new JSONArray(result);
            int size = resultArray.length();

            for(int i = 0; i < size; i++){

                JSONObject movie = resultArray.getJSONObject(i);
                if(MainActivity.sortingStyle == "popular") {
                    MostPopularSortedArrays.poster_path.add(movie.get("poster_path").toString().substring(1));
                    MostPopularSortedArrays.overview.add((String) movie.get("overview"));
                    MostPopularSortedArrays.release_date.add((String) movie.get("release_date"));
                    MostPopularSortedArrays.id.add((int) movie.get("id"));
                    MostPopularSortedArrays.original_title.add((String) movie.get("original_title"));
                    MostPopularSortedArrays.vote_average.add((Double) movie.get("vote_average"));
                }
                else{
                    TopRatedSortedArrays.poster_path.add(movie.get("poster_path").toString().substring(1));
                    TopRatedSortedArrays.overview.add((String) movie.get("overview"));
                    TopRatedSortedArrays.release_date.add((String) movie.get("release_date"));
                    TopRatedSortedArrays.id.add((int) movie.get("id"));
                    TopRatedSortedArrays.original_title.add((String) movie.get("original_title"));
                    TopRatedSortedArrays.vote_average.add((Double) movie.get("vote_average"));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        System.out.println("Done Parsing");
        if(MainActivity.sortingStyle == "popular") {
            new ImagesDownload(mContext).execute(MostPopularSortedArrays.poster_path.toArray(
                    new String[MostPopularSortedArrays.poster_path.size()]));
        }
        else{
            new ImagesDownload(mContext).execute(TopRatedSortedArrays.poster_path.toArray(
                    new String[TopRatedSortedArrays.poster_path.size()]));
        }
        super.onPostExecute(aVoid);
    }
}

