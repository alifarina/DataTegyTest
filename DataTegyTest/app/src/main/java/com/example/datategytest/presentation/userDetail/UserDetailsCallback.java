package com.example.datategytest.presentation.userDetail;

import com.example.datategytest.data.models.UserData;
import com.example.datategytest.data.models.UserProfile;

import java.util.List;

public interface UserDetailsCallback {

    void receiveData(UserProfile userData);

    void noDataFound();

    void onFailure(String message);
}
