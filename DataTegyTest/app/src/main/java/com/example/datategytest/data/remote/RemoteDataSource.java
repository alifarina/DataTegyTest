package com.example.datategytest.data.remote;


import com.example.datategytest.data.models.UserDataContainer;
import com.example.datategytest.data.models.UserProfile;
import com.example.datategytest.presentation.userDetail.UserDetailsCallback;
import com.example.datategytest.presentation.userlist.UserListCallback;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

    DummyDataApis apiInterface;

    public RemoteDataSource() {
        apiInterface = ApiClient.getInstance().getClient();
    }

    public void getUserList(UserListCallback callback) {
        Call<UserDataContainer> call = apiInterface.getAllUsers();
        call.enqueue(new Callback<UserDataContainer>() {
            @Override
            public void onResponse(Call<UserDataContainer> call, Response<UserDataContainer> response) {
                if (response != null) {
                    if (response.code() == 200 && response.body() != null && response.body().getData() != null) {
                        callback.receiveListData(response.body().getData());
                    } else {
                        callback.noDataFound();
                    }
                } else {
                    callback.noDataFound();
                }
            }

            @Override
            public void onFailure(Call<UserDataContainer> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }


    public void getUserProfile(String id, UserDetailsCallback callback) {
        Call<UserProfile> call = apiInterface.getUserProfile(id);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response != null) {
                    if (response.code() == 200 && response.body() != null) {
                        callback.receiveData(response.body());
                    } else {
                        callback.onFailure(response.errorBody().charStream().toString());
                    }
                } else {
                    callback.noDataFound();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
