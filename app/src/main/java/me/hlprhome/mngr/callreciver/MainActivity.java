package me.hlprhome.mngr.callreciver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE;
    int MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS;
    int MY_PERMISSIONS_REQUEST_PROCESS_INTERNET;

    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResults = (TextView) findViewById(R.id.tvResults);

//        if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission has not been granted, therefore prompt the user to grant permission
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_PHONE_STATE},
//                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
//        }
//
//        if (getApplicationContext().checkSelfPermission(Manifest.permission.PROCESS_OUTGOING_CALLS)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission has not been granted, therefore prompt the user to grant permission
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
//                    MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS);
//        }
//        if (getApplicationContext().checkSelfPermission(Manifest.permission.INTERNET)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission has not been granted, therefore prompt the user to grant permission
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.INTERNET},
//                    MY_PERMISSIONS_REQUEST_PROCESS_INTERNET);
//        }
    }

}
