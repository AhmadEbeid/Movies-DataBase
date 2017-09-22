package com.example.aebeid.moviesdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.aebeid.moviesdb.MainActivity.progress;

public class DownloadTask extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private String sortingStyle;

    public DownloadTask(Context c, String sortingStyle) {

        mContext = c;
        this.sortingStyle = sortingStyle;
    }


    @Override
    protected String doInBackground(Void... params) {


        String result = "";
        String urls = "https://api.themoviedb.org/3/movie/" + sortingStyle + "?api_key=621f2fc89fd56f4b3489ccb85b691ff7&language=en-US";
        try {
            URL url = new URL(urls);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int ch = reader.read();
            while(ch != -1){
                result += String.valueOf((char)ch);
                ch = reader.read();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("IOException", "bad connect");
        }
        System.out.println("Done result");
        return result;

    }


    @Override
    protected void onPostExecute(String s) {

        new JsonParsing(mContext).execute(s);

        super.onPostExecute(s);
    }
}
