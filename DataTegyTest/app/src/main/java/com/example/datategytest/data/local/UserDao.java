package com.example.datategytest.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.datategytest.data.models.UserData;
import com.example.datategytest.data.models.UserProfile;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(UserProfile note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<UserData> userDataList);

    @Query("SELECT * FROM userTable")
    List<UserData> getAllUsers();

    @Query("SELECT * FROM user_profile where id = :id")
    UserProfile getUserData(String id);
}
