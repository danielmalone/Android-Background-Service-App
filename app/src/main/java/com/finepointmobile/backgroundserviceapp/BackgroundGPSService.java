package com.finepointmobile.backgroundserviceapp;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by danielmalone on 8/3/17.
 */

public class BackgroundGPSService extends IntentService {

    private static final String TAG = "BackgroundGPSService";

    public BackgroundGPSService() {
        super("BackgroundGPSService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: in service!");

        ResultReceiver rec = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
        bundle.putString("asdf_value", "My Result Value...");

        rec.send(Activity.RESULT_OK, bundle);
    }
}
