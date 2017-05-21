package com.iblogstreet.selfservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/*
 *  @项目名：  SelfService 
 *  @包名：    com.iblogstreet.selfservice
 *  @文件名:   MyBroadcastReceiver
 *  @创建者:   Army
 *  @创建时间:  2017/5/21 0:09
 *  @描述：    TODO
 */
public class MyBroadcastReceiver
        extends BroadcastReceiver
{
    private static final String TAG    = "MyBroadcastReceiver";
    private static final String ACTION = "com.iblogstreet.selfservice.MyBroadcastReceiver";
    private Context mContext;

    public interface OnMyBroadcastReceiverListener {
        void onUpdate(String num);
    }

    private OnMyBroadcastReceiverListener mOnMyBroadcastReceiverListener;

    public MyBroadcastReceiver(Context context, OnMyBroadcastReceiverListener listener) {
        this.mContext = context;
        this.mOnMyBroadcastReceiverListener = listener;
    }

    public void register() {
        IntentFilter filter = new IntentFilter(ACTION);
        mContext.registerReceiver(this, filter);
    }

    public void unregister() {
        mContext.unregisterReceiver(this);
    }

    public static void sendMyBroadcastReceiver(Object object, Context context) {
        Bundle bundle = new Bundle();
        bundle.putString("count", (String) object);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setAction(ACTION);
       // Log.e("MyBroadcastReceiver", "发送广播");
        //发送广播，通知UI层时间改变了
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(ACTION.equals(action)){
            if(mOnMyBroadcastReceiverListener!=null)
                mOnMyBroadcastReceiverListener.onUpdate(intent.getStringExtra("count"));
        }
    }
}
