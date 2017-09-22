package com.example.aebeid.moviesdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    static GridView gridview;
    static ArraysNeeded MostPopularSortedArrays = new ArraysNeeded();
    static ArraysNeeded TopRatedSortedArrays = new ArraysNeeded();
    static ArraysNeeded FavouriteArrays;
    static String sortingStyle = "popular";
    static ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while downloading content...");
        progress.setCancelable(false);

        if(gridview == null) {
            gridview = (GridView) findViewById(R.id.gridView);
            progress.show();
            new DownloadTask(getApplicationContext(), sortingStyle).execute();
        }
        else
        {
            gridview = (GridView) findViewById(R.id.gridView);
            if(sortingStyle == "popular"){
                gridview.setAdapter(new ImageAdapter(getApplicationContext(), MostPopularSortedArrays.SortedBitmaps));
            }
            else
            {
                gridview.setAdapter(new ImageAdapter(getApplicationContext(), TopRatedSortedArrays.SortedBitmaps));
            }
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(getApplication(), DetailsActivity.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.most_popular:
                sortingStyle = "popular";
                item.setChecked(true);
                if(MostPopularSortedArrays.SortedBitmaps.isEmpty()){
                    progress.show();
                    new DownloadTask(getApplicationContext(), sortingStyle).execute();
                }
                else {
                    gridview.setAdapter(new ImageAdapter(getApplicationContext(), MostPopularSortedArrays.SortedBitmaps));
                }
                return true;
            case R.id.top_rated:
                sortingStyle = "top_rated";
                item.setChecked(true);
                if(TopRatedSortedArrays.SortedBitmaps.isEmpty()){
                    progress.show();
                    new DownloadTask(getApplicationContext(), sortingStyle).execute();
                }
                else {
                    gridview.setAdapter(new ImageAdapter(getApplicationContext(), TopRatedSortedArrays.SortedBitmaps));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

