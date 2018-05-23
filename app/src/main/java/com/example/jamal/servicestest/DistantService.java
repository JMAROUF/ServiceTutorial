package com.example.jamal.servicestest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DistantService extends AppCompatActivity {

    BoundService mBoundService;
    boolean mServiceBound=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distant_service);
        final TextView timestampText = (TextView) findViewById(R.id.timestamp_text);
        Button printTimestampButton = (Button) findViewById(R.id.print_timestamp);
        Button stopServiceButon = (Button) findViewById(R.id.stop_service);
        printTimestampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mServiceBound) {
                    timestampText.setText(mBoundService.getTimestamp());
                }
            }
        });
        stopServiceButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mServiceBound){
                    unbindService(mServiceConnection);
                    mServiceBound=false;
                }
                Intent i = new Intent(DistantService.this,BoundService.class);
                stopService(i);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this,BoundService.class);
        startService(intent);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }



    private ServiceConnection mServiceConnection = new ServiceConnection() {
        //connection au service distant
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder= (BoundService.MyBinder) service;
            mBoundService= myBinder.getService();
            mServiceBound=true;
            Toast.makeText(getBaseContext()," service connecté  ",Toast.LENGTH_LONG).show();
        }
        // deconnexion du service distant
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound=false;
            Toast.makeText(getBaseContext()," service deconnecté  ",Toast.LENGTH_LONG).show();
        }
    };
}
