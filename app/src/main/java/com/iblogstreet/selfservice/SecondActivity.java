package com.iblogstreet.selfservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
 *  @项目名：  SelfService 
 *  @包名：    com.iblogstreet.selfservice
 *  @文件名:   SecondActivity
 *  @创建者:   Army
 *  @创建时间:  2017/5/20 21:25
 *  @描述：    TODO
 */
public class SecondActivity
        extends AppCompatActivity
{
    private static final String TAG = "SecondActivity";
    MyBroadcastReceiver myBroadcastReceiver;
    TextView            mTvMsg;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mTvMsg.setText(msg.obj + "");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btnStopService = (Button) findViewById(R.id.btnStopService);
        mTvMsg = (TextView) findViewById(R.id.tvMsg);
        myBroadcastReceiver = new MyBroadcastReceiver(this,
                                                      new MyBroadcastReceiver.OnMyBroadcastReceiverListener() {
                                                          @Override
                                                          public void onUpdate(String num) {
                                                              Message message = mHandler.obtainMessage();
                                                              message.obj = num;
                                                              mHandler.sendMessage(message);
                                                          }
                                                      });
        myBroadcastReceiver.register();
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MyServices.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myBroadcastReceiver.unregister();
    }
}
