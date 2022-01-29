package com.example.datategytest.presentation.userDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datategytest.data.local.LocalDataSource;
import com.example.datategytest.data.models.UserProfile;
import com.example.datategytest.data.remote.RemoteDataSource;
import com.example.datategytest.utils.AppUtils;

public class UserDetailsPresenter {


    private UserDetailViewModel viewModel;
    private MutableLiveData<UserProfile> userLiveData = new MutableLiveData();
    private LocalDataSource localDataSource;
    //    private final RemoteDataSource remoteDataSource;
    RemoteDataSource remoteDataSource;


    public UserDetailsPresenter(UserDetailViewModel viewModel,RemoteDataSource remoteDataSource,LocalDataSource localDataSource) {
        this.viewModel = viewModel;
//        localDataSource = new LocalDataSource(viewModel.getContext());
//        remoteDataSource = new RemoteDataSource();
        this.remoteDataSource = remoteDataSource;
        this.localDataSource=localDataSource;

    }

    public void getUserDetails(String user_id) {
        viewModel.showProgress();
        if (AppUtils.isNetworkAvailable(viewModel.getContext())) {
            remoteDataSource.getUserProfile(user_id, new UserDetailsCallback() {

                @Override
                public void receiveData(UserProfile userData) {
                    viewModel.hideProgress();
                    userLiveData.postValue(userData);
                    localDataSource.saveUserProfile(userData);
                }

                @Override
                public void noDataFound() {
                    viewModel.hideProgress();
                }

                @Override
                public void onFailure(String message) {
                    viewModel.hideProgress();
                    viewModel.onFailure(message);
                }
            });
        } else {
            localDataSource.getUserProfile(user_id, new UserDetailsCallback() {

                @Override
                public void receiveData(UserProfile userData) {
                    viewModel.hideProgress();
                    userLiveData.postValue(userData);
                }

                @Override
                public void noDataFound() {
                    viewModel.hideProgress();
                }

                @Override
                public void onFailure(String message) {
                    viewModel.hideProgress();
                    viewModel.onFailure(message);
                }
            });
        }
        // repository.getUserData("");
    }


    public LiveData<UserProfile> getUserProfile() {
        return userLiveData;
    }
}
