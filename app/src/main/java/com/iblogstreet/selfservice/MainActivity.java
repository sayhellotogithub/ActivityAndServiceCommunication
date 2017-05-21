package com.iblogstreet.selfservice;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity
        extends AppCompatActivity
        implements IUpdateInterface
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mBtnStartService       = (Button) findViewById(R.id.btnStartService);
        Button mBtnJumpThridActivity  = (Button) findViewById(R.id.btnJumpThridActivity);
        Button mBtnJumpSecondActivity = (Button) findViewById(R.id.btnJumpSecondActivity);
        mBtnJumpThridActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThridActivity.class));
            }
        });
        mBtnJumpSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        mBtnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
    }

    public void startService() {
        if (!isServiceRunning()) {
            Intent        intent        = new Intent(this, MyServices.class);
            ComponentName componentName = startService(intent);
        }

    }

    @Override
    public void onUpdate(Object object) {

    }

    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.iblogstreet.selfservice.MyServices".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
