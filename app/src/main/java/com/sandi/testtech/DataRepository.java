package com.sandi.testtech;

import androidx.lifecycle.MutableLiveData;

import com.sandi.testtech.model.DataExample;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class DataRepository {
    private ApiInterface apiInterface;

    private DataSource dataSource;

    public DataRepository(ApiInterface apiInterface, DataSource dataSource) {
        this.apiInterface = apiInterface;
    }

    public MutableLiveData<List<DataExample>> fetchData(CompositeDisposable compositeDisposable) {
        dataSource = new DataSource(apiInterface, compositeDisposable);
        return dataSource.fetchData();
    }

    public void cancel() {

    }
}
