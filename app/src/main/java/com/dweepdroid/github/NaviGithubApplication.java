package com.dweepdroid.github;

import android.app.Application;
import android.content.Context;

import com.dweepdroid.github.network.NetworkController;

public class NaviGithubApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        NetworkController.getInstance().createRetrofit();

    }

    public static Context getContext(){
        return context;
    }
}
