package com.example.datategytest.presentation.userDetail;

import android.content.Context;

public interface UserDetailViewModel {

    void showProgress();

    void hideProgress();

    void onFailure(String message);

    void noDataFound();

    Context getContext();
}
