package com.example.aebeid.moviesdb;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<Bitmap> bitmaps;

    public ImageAdapter(Context c, ArrayList<Bitmap> bitmaps) {
        mContext = c;
        this.bitmaps = bitmaps;
    }

    public int getCount() {
        return bitmaps.size();
    }

    public Object getItem(int position) { return null; }

    public long getItemId(int position) { return 0; }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        Bitmap bitmap = null;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setMinimumHeight();
            imageView.setMinimumHeight(513);
            imageView.setMinimumWidth(342);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }

        bitmap = bitmaps.get(position);

        imageView.setImageBitmap(bitmap);

        return imageView;
    }
}

