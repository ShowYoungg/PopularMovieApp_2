package com.example.android.popularmovieapp2.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmovieapp2.Adapters.MovieJsonUtils;
import com.example.android.popularmovieapp2.Objects.Review;
import com.example.android.popularmovieapp2.Objects.Youtube;

import java.net.URL;
import java.util.ArrayList;

import static com.example.android.popularmovieapp2.Activities.DetailActivity.buildUrl3;
import static com.example.android.popularmovieapp2.Activities.DetailActivity.movieId;
import static com.example.android.popularmovieapp2.Activities.DetailActivity.reviews;
import static com.example.android.popularmovieapp2.Activities.MainActivity.getResponseFromHttpUrl;

/**
 * Created by SHOW on 9/4/2018.
 */

public class ReviewLiveData extends android.arch.lifecycle.LiveData<ArrayList<Review>> {

        private final Context context;

        public ReviewLiveData(Context context){
            this.context= context;
            loadReview();
        }


    private void loadReview(){
        new AsyncTask<Void, Void, ArrayList<Review>>() {
            @Override
            protected ArrayList<Review> doInBackground(Void... voids) {
                String g = String.valueOf(movieId);

                // Create URL object
                URL url = buildUrl3(g);

                // Perform HTTP request to the URL and receive a JSON response back
                String jsonResponse = "";
                try {
                    jsonResponse = getResponseFromHttpUrl(url);
                } catch (Exception e) {

                    e.printStackTrace();
                }

                reviews = MovieJsonUtils.parseReview(jsonResponse);
                return reviews;
            }

            @Override
            protected void onPostExecute(ArrayList<Review> strings) {

                setValue(reviews);
            }
        }.execute();
    }
}
