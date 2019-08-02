package me.hlprhome.mngr.callreciver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.StringTokenizer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE;
    int MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS;
    int MY_PERMISSIONS_REQUEST_PROCESS_INTERNET;

    TextView tvResults;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResults = (TextView) findViewById(R.id.tvResults);
        btnShow = findViewById(R.id.btnShow);

// Permissions for Android versions
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
    public void btnShow (View v){
        File file = getApplicationContext().getFileStreamPath("Data.txt");
        String linefromfile;
        if (file.exists()){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));
                while ((linefromfile = reader.readLine()) != null){
                    tvResults.setText(linefromfile);



                }
                reader.close();


            } catch (IOException e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

}
