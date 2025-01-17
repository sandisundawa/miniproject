package com.sandi.testtech;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.sandi.testtech.model.DataExample;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DataSource {
    private final ApiInterface apiService;
    private final CompositeDisposable compositeDisposable;


    public DataSource(ApiInterface apiService, CompositeDisposable compositeDisposable) {
        this.apiService = apiService;
        this.compositeDisposable = compositeDisposable;
    }


    private final MutableLiveData<List<DataExample>> _dataResponse = new MutableLiveData<>();
    public MutableLiveData<List<DataExample>> getDataResponse() {
        return _dataResponse;
    }

    public void fetchData() {
        try {
            compositeDisposable.add(
                    apiService.getData()
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    data -> {
                                        _dataResponse.postValue(data);
                                    },
                                    throwable -> {
                                        Log.e("DataSource", throwable.getMessage() != null ? throwable.getMessage() : "Error");
                                    }
                            )
            );
        } catch (Exception e) {
            Log.e("DataSource", e.getMessage() != null ? e.getMessage() : "Error");
        }
    }
}
