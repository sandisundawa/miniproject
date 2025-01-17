package com.sandi.testtech;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.sandi.testtech.model.DataExample;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DataSource {
    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable;

    private final MutableLiveData<List<DataExample>> _dataResponse = new MutableLiveData<>();
    public MutableLiveData<List<DataExample>> getDataResponse() {
        return _dataResponse;
    }

    public DataSource(ApiInterface apiInterface, CompositeDisposable compositeDisposable) {
        this.apiInterface = apiInterface;
        this.compositeDisposable = compositeDisposable;
    }

    public MutableLiveData<List<DataExample>> fetchData() {
        try {
            compositeDisposable.add(
                    apiInterface.getData().
                            subscribeOn(Schedulers.io()).subscribe(dataExamples -> {
                        _dataResponse.postValue(dataExamples);

                    })
            );
        } catch (Exception e) {
            Log.d("error", e.toString());
        }
    }

}
