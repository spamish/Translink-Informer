package com.spamish.project.translinkinformer.net_tools;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connectivity {
    public Boolean isNetworkAvailable(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
