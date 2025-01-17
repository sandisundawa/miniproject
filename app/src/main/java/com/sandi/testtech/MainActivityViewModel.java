package com.sandi.testtech;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sandi.testtech.model.DataExample;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {

    private final DataRepository dataRepository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<DataExample>> listData;

    public MainActivityViewModel() {
        this.dataRepository = new DataRepository(ApiClient.getClient());
    }

    public MainActivityViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void fetchListData() {
        listData = dataRepository.fetchData(compositeDisposable);

    }

    public MutableLiveData<List<DataExample>> getListData() {
        return listData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}