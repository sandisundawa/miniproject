package com.sandi.testtech;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static ApiInterface getClient() {
        Interceptor requestInterceptor = chain -> {
            okhttp3.HttpUrl url = chain.request().url().newBuilder().build();

            Request request = chain.request().newBuilder().url(url).build();

            return chain.proceed(request);
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(requestInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS).build();

        return new Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface.class);
    }
}
