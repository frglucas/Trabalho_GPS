package com.edu.uniritter.gpsapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GPSBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "GPSBroadcastReceiver";
    private GPSService service;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w(TAG, "onReceive: " + intent.getAction());

    }
}
