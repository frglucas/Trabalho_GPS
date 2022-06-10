package com.edu.uniritter.gpsapplication.receiver;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GPSService extends Service {

    public static final String TAG = "GPService";
    LocationManager locationManager;
    Location lastLoc = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 3, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                float dist = 0;
                Location loc = location;


                Log.d(TAG, "onLocatinResult: gravou");
                if(lastLoc != null){
                    dist = loc.distanceTo(lastLoc);
                }
                lastLoc = loc;

                Log.w(TAG, "onLocationResult: " + dist + "m acc:" + loc.getAccuracy());
                Toast.makeText(GPSService.this, dist + "m", Toast.LENGTH_SHORT).show();
            }
        });

        if (intent == null) {
            return START_NOT_STICKY;
        }

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
