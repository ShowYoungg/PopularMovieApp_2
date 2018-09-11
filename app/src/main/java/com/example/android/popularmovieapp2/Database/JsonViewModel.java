package com.example.android.popularmovieapp2.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmovieapp2.Objects.Youtube;

import java.util.ArrayList;

/**
 * Created by SHOW on 9/4/2018.
 */

public class JsonViewModel extends AndroidViewModel {

    private final LiveData dat;

    public JsonViewModel(@NonNull Application application) {
        super(application);
        dat = new JsonLiveData(application);
    }

    public LiveData<ArrayList<Youtube>> getData(){
        return dat;
    }
}