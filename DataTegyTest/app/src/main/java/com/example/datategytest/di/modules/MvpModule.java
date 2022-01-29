package com.example.datategytest.di.modules;

import android.content.Context;

import com.example.datategytest.R;
import com.example.datategytest.data.local.LocalDataSource;
import com.example.datategytest.data.remote.RemoteDataSource;
import com.example.datategytest.presentation.userDetail.UserDetailViewModel;
import com.example.datategytest.presentation.userDetail.UserDetailsPresenter;
import com.example.datategytest.presentation.userlist.UserListPresenter;
import com.example.datategytest.presentation.userlist.UserListViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpModule {

    private UserListViewModel viewCallBack;

    public MvpModule(UserListViewModel viewCallBack) {
        this.viewCallBack = viewCallBack;
    }

    @Provides
    UserListViewModel providesListViewModel() {
        return viewCallBack;
    }

    private UserDetailViewModel viewDetailsCallBack;

    public MvpModule(UserDetailViewModel viewCallBack) {
        this.viewDetailsCallBack = viewCallBack;
    }

    @Provides
    UserDetailViewModel providesDetailsViewModel() {
        return viewDetailsCallBack;
    }

    @Provides
    RemoteDataSource provideRemoteSource() {
        return new RemoteDataSource();
    }


    @Provides
    LocalDataSource provideLocalDataSource(Context context) {
        return new LocalDataSource(context);
    }

    @Provides
    UserListPresenter provideListPresenter(UserListViewModel model, RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        return new UserListPresenter(model, remoteDataSource, localDataSource);
    }

    @Provides
    UserDetailsPresenter provideDetailsPresenter(UserDetailViewModel model, RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        return new UserDetailsPresenter(model, remoteDataSource, localDataSource);
    }
}
