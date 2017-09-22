package com.example.aebeid.moviesdb;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.aebeid.moviesdb.MainActivity.MostPopularSortedArrays;
import static com.example.aebeid.moviesdb.MainActivity.TopRatedSortedArrays;
import static com.example.aebeid.moviesdb.MainActivity.gridview;
import static com.example.aebeid.moviesdb.MainActivity.progress;

public class ImagesDownload extends AsyncTask<String, Void, ArrayList<Bitmap>>{

        private Context mContext;

        public ImagesDownload(Context c) {
                mContext = c;
        }



    @Override
    protected ArrayList<Bitmap> doInBackground(String... urls) {

        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        try {
            if(MainActivity.sortingStyle == "popular"){
                for(int i = 0; i < MostPopularSortedArrays.poster_path.size(); i++) {
                    MostPopularSortedArrays.SortedBitmaps.add(Picasso.with(mContext)
                            .load("http://image.tmdb.org/t/p/w185/" + urls[i]).get());
                }
            }
            else{
                for(int i = 0; i < TopRatedSortedArrays.poster_path.size(); i++) {
                    TopRatedSortedArrays.SortedBitmaps.add(Picasso.with(mContext)
                            .load("http://image.tmdb.org/t/p/w185/" + urls[i]).get());
                }
            }

            return bitmaps;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
        System.out.println("Done Image Downloading");
        if(MainActivity.sortingStyle == "popular"){
            gridview.setAdapter(new ImageAdapter(mContext, MostPopularSortedArrays.SortedBitmaps));
            progress.dismiss();
        }
        else {
            gridview.setAdapter(new ImageAdapter(mContext, TopRatedSortedArrays.SortedBitmaps));
            progress.dismiss();
        }
        super.onPostExecute(bitmaps);
    }
}
