package com.example.datategytest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility class
 */
public class AppUtils {

    /**
     * check network connection
     * @param context
     * @return
     */
    public static Boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] activeNetworkInfo = connectivityManager.getAllNetworkInfo();
        for (int i = 0; i < activeNetworkInfo.length; i++) {
            if (activeNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED ) {
                return true;
            }
        }
        return false;
    }
}
