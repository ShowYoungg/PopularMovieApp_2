package com.example.android.popularmovieapp2.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmovieapp2.Adapters.MovieJsonUtils;
import com.example.android.popularmovieapp2.Objects.ArrayListObjects;
import com.example.android.popularmovieapp2.Objects.DataObject;
import com.example.android.popularmovieapp2.Objects.Review;
import com.example.android.popularmovieapp2.Objects.Youtube;

import java.net.URL;
import java.util.ArrayList;

import static com.example.android.popularmovieapp2.Activities.DetailActivity.buildUrl2;
import static com.example.android.popularmovieapp2.Activities.DetailActivity.buildUrl3;
import static com.example.android.popularmovieapp2.Activities.DetailActivity.movieId;
import static com.example.android.popularmovieapp2.Activities.DetailActivity.reviews;
import static com.example.android.popularmovieapp2.Activities.DetailActivity.youtube;
import static com.example.android.popularmovieapp2.Activities.MainActivity.getResponseFromHttpUrl;

/**
 * Created by SHOW on 9/4/2018.
 */

public class JsonLiveData extends android.arch.lifecycle.LiveData<ArrayList<Youtube>> {

        private final Context context;

        public JsonLiveData(Context context){
            this.context= context;
            loadData();
        }


    private void loadData(){
        new AsyncTask<Void, Void, ArrayList<Youtube>>() {
            @Override
            protected ArrayList<Youtube> doInBackground(Void... voids) {
                String g = String.valueOf(movieId);

                // Create URL object
                URL url = buildUrl2(g);

                // Perform HTTP request to the URL and receive a JSON response back
                String jsonResponse = "";
                try {
                    jsonResponse = getResponseFromHttpUrl(url);
                } catch (Exception e) {

                    e.printStackTrace();
                }

                youtube = MovieJsonUtils.parseToYouTube(jsonResponse);
                return youtube;
            }

            @Override
            protected void onPostExecute(ArrayList<Youtube> strings) {

                setValue(youtube);
            }
        }.execute();
    }
}
