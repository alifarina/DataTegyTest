package com.example.datategytest.data.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datategytest.data.models.UserData;
import com.example.datategytest.data.models.UserProfile;
import com.example.datategytest.presentation.userDetail.UserDetailsCallback;
import com.example.datategytest.presentation.userlist.UserListCallback;

import java.util.List;

import javax.inject.Inject;

public class LocalDataSource {

    private final UserDatabase database;
    private final UserDao dataDao;
    private MutableLiveData<List<UserData>> userListLiveData = new MutableLiveData<>();

    public LocalDataSource(Context context) {
        database = UserDatabase.getInstance(context);
        dataDao = database.userDao();
    }

    public void saveUserList(List<UserData> dataList) {
        new InsertDataList(dataList).execute();
    }

    public void saveUserProfile(UserProfile userProfile) {
        new InsertUserProfile(userProfile).execute();
    }

    public void getUserProfile(String user_id, UserDetailsCallback callback) {
        new ViewData(user_id, callback).execute();
    }

    public void getUserList(UserListCallback callback) {
        new GetAllData(callback).execute();
    }

    public LiveData<List<UserData>> getUserLiveData() {
        return userListLiveData;
    }

    private class InsertDataList extends AsyncTask<Void, Void, Void> {

        List<UserData> dataList;

        public InsertDataList(List<UserData> dataList) {
            this.dataList = dataList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataDao.InsertAll(dataList);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }

    private class InsertUserProfile extends AsyncTask<Void, Void, Void> {

        UserProfile userProfile;

        public InsertUserProfile(UserProfile dataList) {
            this.userProfile = dataList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataDao.insertProfile(userProfile);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }

    private class ViewData extends AsyncTask<Void, Void, UserProfile> {

        private String user_id;
        private UserDetailsCallback callback;

        public ViewData(String user_id, UserDetailsCallback callback) {
            this.user_id = user_id;
            this.callback = callback;
        }

        @Override
        protected UserProfile doInBackground(Void... voids) {
            UserProfile data = dataDao.getUserData(user_id);
            return data;
        }

        @Override
        protected void onPostExecute(UserProfile data) {
            super.onPostExecute(data);
            if (data != null) {
                callback.receiveData(data);
            } else {
                callback.noDataFound();
            }
        }
    }

    private class GetAllData extends AsyncTask<Void, Void, List<UserData>> {

        private UserListCallback callback;

        public GetAllData(UserListCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<UserData> doInBackground(Void... voids) {
            List<UserData> data = dataDao.getAllUsers();
            return data;
        }

        @Override
        protected void onPostExecute(List<UserData> data) {

            if (data != null && data.size() > 0) {
//                userListLiveData.postValue(data);
                callback.receiveListData(data);
            } else {
                callback.noDataFound();
            }
            super.onPostExecute(data);
//            callback.receiveListData(dataList);
        }
    }
}
