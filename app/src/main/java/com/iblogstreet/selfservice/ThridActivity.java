package com.iblogstreet.selfservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
 *  @项目名：  SelfService 
 *  @包名：    com.iblogstreet.selfservice
 *  @文件名:   ThridActivity
 *  @创建者:   Army
 *  @创建时间:  2017/5/21 0:49
 *  @描述：    TODO
 */
public class ThridActivity
        extends AppCompatActivity
        implements IUpdateInterface
{
    private static final String TAG = "ThridActivity";
    TextView mTvMsg;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mTvMsg.setText(msg.obj + "");
            Log.e(TAG, msg.obj+"");
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btnStopService = (Button) findViewById(R.id.btnStopService);
        mTvMsg = (TextView) findViewById(R.id.tvMsg);

        MyServices.addActivity(this);
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThridActivity.this, MyServices.class);
                stopService(intent);
            }
        });
    }

    @Override
    public void onUpdate(Object object) {
        Message message = mHandler.obtainMessage();
        message.obj = object;
        mHandler.sendMessage(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyServices.removeActivity(this);
    }
}
