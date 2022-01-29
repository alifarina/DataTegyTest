package com.example.datategytest.presentation.userlist;

import androidx.lifecycle.LiveData;

import com.example.datategytest.data.models.UserData;

import java.util.List;

public interface UserListCallback {

    void receiveListData(List<UserData> userDataList);

    void noDataFound();

    void onFailure(String message);
}
