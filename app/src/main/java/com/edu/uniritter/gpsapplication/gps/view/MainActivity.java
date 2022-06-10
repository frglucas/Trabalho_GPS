package com.edu.uniritter.gpsapplication.gps.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.edu.uniritter.gpsapplication.R;
import com.edu.uniritter.gpsapplication.gps.viewmodel.SensorViewModel;
import com.edu.uniritter.gpsapplication.receiver.GPSBroadcastReceiver;
import com.edu.uniritter.gpsapplication.sqlite.DBHelper;
import com.edu.uniritter.gpsapplication.view.GPSActivity;

public class MainActivity extends AppCompatActivity {

    private SensorViewModel viewModel;
    BroadcastReceiver bcr;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bcr = new GPSBroadcastReceiver();
        IntentFilter intf = new IntentFilter("com.edu.uniritter.GPS_START");

        registerReceiver(bcr, intf);

        ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
                    Boolean backgroundLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_BACKGROUND_LOCATION, false);
                    if (fineLocationGranted != null && fineLocationGranted && backgroundLocationGranted) {
                        Log.d("MainActivity", "onCreate: autorizado GPS");
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        Log.d("MainActivity", "onCreate: autorizado GPS(loc. aproximada)");
                    } else {
                        Log.d("MainActivity", "onCreate: não autorizado GPS");
                    }
                });

        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

        viewModel = new ViewModelProvider(this).get(SensorViewModel.class);
        viewModel.setContexto(this);

        findViewById(R.id.buttonGPS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), GPSActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase writableDataBase = dbHelper.getWritableDatabase();
    }
}