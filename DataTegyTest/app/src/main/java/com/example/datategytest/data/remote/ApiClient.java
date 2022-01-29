package com.example.datategytest.data.remote;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient client = null;
    private Retrofit retrofit;

    private ApiClient() {
        Log.d("Apiclient","oncreate");
    }

    public static ApiClient getInstance() {
        if (client == null) {
            client = new ApiClient();
        }
        return client;
    }

    /**
     * create retrofit object
     * @return
     */
    public DummyDataApis getClient() {
        Log.d("Apiclient","getClient");
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://dummyapi.io/data/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        DummyDataApis apiInterface = retrofit.create(DummyDataApis.class);

        return apiInterface;
    }

}
