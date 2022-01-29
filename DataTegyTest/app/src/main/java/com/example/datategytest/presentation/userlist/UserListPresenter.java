package com.example.datategytest.presentation.userlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datategytest.data.local.LocalDataSource;
import com.example.datategytest.data.models.UserData;
import com.example.datategytest.data.remote.RemoteDataSource;
import com.example.datategytest.utils.AppUtils;

import java.util.List;

public class UserListPresenter {

    private UserListViewModel viewModel;
    private MutableLiveData<List<UserData>> userListLiveData = new MutableLiveData();
    private LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;


    public UserListPresenter(UserListViewModel viewModel, RemoteDataSource remoteDataSource,LocalDataSource localDataSource) {
        this.viewModel = viewModel;
        this.remoteDataSource = remoteDataSource;
        this.localDataSource=localDataSource;
        getUsersList();

    }

    public void getUsersList() {
        viewModel.showProgress();
        if (AppUtils.isNetworkAvailable(viewModel.getContext())) {
            remoteDataSource.getUserList(new UserListCallback() {

                @Override
                public void receiveListData(List<UserData> userDataList) {
                    viewModel.hideProgress();
                    userListLiveData.postValue(userDataList);
                    localDataSource.saveUserList(userDataList);
                }

                @Override
                public void noDataFound() {
                    viewModel.hideProgress();
                    viewModel.noDataFound();
                }

                @Override
                public void onFailure(String message) {
                    viewModel.hideProgress();
                    viewModel.onFailure(message);
                }
            });
        } else {
            localDataSource.getUserList(new UserListCallback() {

                @Override
                public void receiveListData(List<UserData> userDataList) {
                    viewModel.hideProgress();
                    userListLiveData.postValue(userDataList);
                }

                @Override
                public void noDataFound() {
                    viewModel.hideProgress();
                    viewModel.noDataFound();
                }

                @Override
                public void onFailure(String message) {
                    viewModel.hideProgress();
                    viewModel.onFailure(message);
                }
            });
        }
    }

    public LiveData<List<UserData>> getAllUsers() {
        return userListLiveData;
    }
}
