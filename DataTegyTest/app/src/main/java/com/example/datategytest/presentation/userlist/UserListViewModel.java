package com.example.datategytest.presentation.userlist;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.datategytest.data.models.UserData;

import java.util.List;

public interface UserListViewModel {

    void showProgress();

    void hideProgress();

    void onFailure(String message);

    Context getContext();

    void noDataFound();
}
