package com.example.android.popularmovieapp2.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmovieapp2.Objects.DataObject;

import java.util.List;
import java.util.concurrent.ExecutorService;



/**
 * Created by SHOW on 9/4/2018.
 */

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<DataObject>> dataObj;
    private static ExecutorService executorService;
    private static AppDatabase mDb;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mDb = AppDatabase.getInstance(this.getApplication());
        dataObj = mDb .dataDao().loadAllData();
    }

    public LiveData<List<DataObject>> getDataObj() {
        return dataObj;
    }

    public static void deleteMovie(final DataObject data){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mDb.dataDao().deleteData(data);
            }
        });
    }
}
