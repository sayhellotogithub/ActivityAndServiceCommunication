package com.iblogstreet.selfservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  SelfService 
 *  @包名：    com.iblogstreet.selfservice
 *  @文件名:   MyServices
 *  @创建者:   Army
 *  @创建时间:  2017/5/20 21:28
 *  @描述：    TODO
 */
public class MyServices
        extends Service
{
    private static final String TAG = "MyServices";
    private boolean isStop;
    private Thread  mThread;
    private static List<IUpdateInterface> mActivityList = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyServices", "开启服务");
        isStop = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mThread = new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                while (isStop) {
                    Log.e("MyServices", "i" + i);
                    i++;
                    update(i + "");
                    MyBroadcastReceiver.sendMyBroadcastReceiver(i + "", getApplication());
                }
            }
        });
        mThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public static void addActivity(IUpdateInterface iUpdateInterface) {
        mActivityList.add(iUpdateInterface);
    }

    public void update(Object object) {
        for (IUpdateInterface activity : mActivityList) {
            activity.onUpdate(object);
        }
    }

    public static void removeActivity(IUpdateInterface iUpdateInterface) {
        mActivityList.remove(iUpdateInterface);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = false;
        if (mThread.isAlive()) {
            mThread.interrupt();
        }
        Log.e("MyServices", "onDestroy");
    }
}
