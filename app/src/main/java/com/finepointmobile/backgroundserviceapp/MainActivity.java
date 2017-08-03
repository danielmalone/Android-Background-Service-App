package com.finepointmobile.backgroundserviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    FloatingActionButton mFab;
    GPSResultReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BackgroundGPSService.class);
                intent.putExtra("receiver", "hello-everyone!");
                startService(intent);
            }
        });

        setupServiceReceiver();
    }

    public void setupServiceReceiver() {
        mReceiver = new GPSResultReceiver(new Handler());

        mReceiver.setReceiver(new GPSResultReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                Log.d(TAG, "onReceiveResult: resultCode: " + resultCode);
                Log.d(TAG, "onReceiveResult: resultdata: " + resultData);
            }
        });
    }
}
