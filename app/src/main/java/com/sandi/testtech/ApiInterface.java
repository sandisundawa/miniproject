package com.sandi.testtech;

import com.sandi.testtech.model.DataExample;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("posts")
    Observable<List<DataExample>> getData();
}
