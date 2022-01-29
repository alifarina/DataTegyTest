package com.example.datategytest.data.remote;

import com.example.datategytest.data.models.UserDataContainer;
import com.example.datategytest.data.models.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DummyDataApis {

    /**
     * Get fun to return all user from the API
     * @return
     */
    @Headers({
            "app-id: 61ebfa6c8372f433e874b2a0"
    })
    @GET("user?limit=10")
    Call<UserDataContainer> getAllUsers();


    /**
     * Get fun to return user profile
     * @param user_id
     * @return
     */
    @Headers({
            "app-id: 61ebfa6c8372f433e874b2a0"
    })
    @GET("user/{user_id}")
    Call<UserProfile> getUserProfile(@Path("user_id") String user_id);


}
