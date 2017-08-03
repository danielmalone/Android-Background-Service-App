package com.finepointmobile.backgroundserviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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
                oldStartService();
            }
        });

        setupServiceReceiver();
    }

    public void oldStartService() {
        Intent intent = new Intent(this, BackgroundGPSService.class);
        intent.putExtra("receiver", mReceiver);
        startService(intent);
    }


    public void onStartService() {
        Intent i = new Intent(this, BackgroundGPSService.class);
        i.putExtra("foo", "bar");
        i.putExtra("receiver", mReceiver);
        startService(i);
    }

    // Setup the callback for when data is received from the service
    public void setupServiceReceiver() {
        mReceiver = new GPSResultReceiver(new Handler());
        // This is where we specify what happens when data is received from the service
        mReceiver.setReceiver(new GPSResultReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(MainActivity.this, resultValue, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
