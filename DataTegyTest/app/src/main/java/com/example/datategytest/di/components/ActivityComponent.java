package com.example.datategytest.di.components;

import com.example.datategytest.di.modules.MvpModule;
import com.example.datategytest.di.scope.ActivityScope;
import com.example.datategytest.presentation.userDetail.UserDetailsPresenter;
import com.example.datategytest.presentation.userlist.UserListPresenter;
import com.example.datategytest.ui.UserDetailActivity;
import com.example.datategytest.ui.UserListActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MvpModule.class)
public interface ActivityComponent {

    void  inject(UserListActivity mainActivity);

    UserListPresenter getMainPresenter();

    void inject(UserDetailActivity detailActivity);

    UserDetailsPresenter getDetailsPresenter();
}
