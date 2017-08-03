package com.finepointmobile.backgroundserviceapp;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

/**
 * Created by danielmalone on 8/3/17.
 */

public class BackgroundGPSService extends IntentService {

    private static final String TAG = "BackgroundGPSService";

    public BackgroundGPSService() {
        super("BackgroundGPSService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Extract the receiver passed into the service
        ResultReceiver rec = intent.getParcelableExtra("receiver");
        // Extract additional values from the bundle
        String val = intent.getStringExtra("foo");
        // To send a message to the Activity, create a pass a Bundle
        Bundle bundle = new Bundle();
        bundle.putString("resultValue", "My Result Value. Passed in: " + val);
        // Here we call send passing a resultCode and the bundle of extras
        rec.send(Activity.RESULT_OK, bundle);
    }
}
