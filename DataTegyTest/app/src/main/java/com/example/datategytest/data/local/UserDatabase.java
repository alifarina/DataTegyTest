package com.example.datategytest.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.datategytest.data.models.UserData;
import com.example.datategytest.data.models.UserProfile;

import javax.inject.Inject;

@Database(entities = {UserData.class, UserProfile.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract UserDao userDao();


    // creating singleton instance of database
    public static synchronized UserDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, UserDatabase.class , "Database_name")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    } //synchronized : only one thread can access this method

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // new PopulateDb(instance).execute();
        }
    };
}
