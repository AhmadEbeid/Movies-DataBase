package com.example.aebeid.moviesdb;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.aebeid.moviesdb.MainActivity.MostPopularSortedArrays;
import static com.example.aebeid.moviesdb.MainActivity.TopRatedSortedArrays;
import static com.example.aebeid.moviesdb.MainActivity.sortingStyle;

public class DetailsActivity extends AppCompatActivity {


    ImageView imageView;
    TextView releaseDate;
    TextView rating;
    TextView overview;
    TextView title;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        position = i.getIntExtra("position", -1);

        imageView = (ImageView) findViewById(R.id.imageView2);
        releaseDate = (TextView) findViewById(R.id.textView5);
        rating = (TextView) findViewById(R.id.textView6);
        overview = (TextView) findViewById(R.id.textView2);
        title = (TextView) findViewById(R.id.textView);

        if(sortingStyle == "popular"){
            imageView.setImageBitmap(MostPopularSortedArrays.SortedBitmaps.get(position));
            releaseDate.setText(MostPopularSortedArrays.release_date.get(position));
            rating.setText(MostPopularSortedArrays.vote_average.get(position).toString() + "/10");
            overview.setText(MostPopularSortedArrays.overview.get(position));
            title.setText(MostPopularSortedArrays.original_title.get(position));

        }
        else{
            imageView.setImageBitmap(TopRatedSortedArrays.SortedBitmaps.get(position));
            releaseDate.setText(TopRatedSortedArrays.release_date.get(position));
            rating.setText(TopRatedSortedArrays.vote_average.get(position).toString() + "/10");
            overview.setText(TopRatedSortedArrays.overview.get(position));
            title.setText(TopRatedSortedArrays.original_title.get(position));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fav(View v){
        if(sortingStyle == "popular"){
            MostPopularSortedArrays.SortedBitmaps.get(position);
            MostPopularSortedArrays.release_date.get(position);
            MostPopularSortedArrays.vote_average.get(position).toString() + "/10";
            MostPopularSortedArrays.overview.get(position);
            MostPopularSortedArrays.original_title.get(position);

        }
        else{
            TopRatedSortedArrays.SortedBitmaps.get(position);
            TopRatedSortedArrays.release_date.get(position);
            TopRatedSortedArrays.vote_average.get(position).toString() + "/10";
            TopRatedSortedArrays.overview.get(position);
            TopRatedSortedArrays.original_title.get(position);
        }



    }
}
