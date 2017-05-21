package com.iblogstreet.selfservice;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/*
 *  @项目名：  SelfService 
 *  @包名：    com.iblogstreet.selfservice
 *  @文件名:   App
 *  @创建者:   Army
 *  @创建时间:  2017/5/21 1:02
 *  @描述：    TODO
 */
public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e(TAG,"onTerminate");
        stopService(new Intent(this, MyServices.class));
    }
}
