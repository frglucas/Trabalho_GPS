package com.edu.uniritter.gpsapplication.gps.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.edu.uniritter.gpsapplication.R;
import com.edu.uniritter.gpsapplication.receiver.GPSService;

public class LandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this, GPSService.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
    }
}