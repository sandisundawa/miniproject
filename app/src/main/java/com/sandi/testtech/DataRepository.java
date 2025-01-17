package com.sandi.testtech;

import androidx.lifecycle.MutableLiveData;

import com.sandi.testtech.model.DataExample;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class DataRepository {
    private final ApiInterface apiService;
    private DataSource dataSource;

    public DataRepository(ApiInterface apiService) {
        this.apiService = apiService;
    }

    public MutableLiveData<List<DataExample>> fetchData(CompositeDisposable compositeDisposable) {
        dataSource = new DataSource(apiService, compositeDisposable);
        dataSource.fetchData();
        return dataSource.getDataResponse();
    }
}
