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

import com.example.jamal.servicestest.BoundService.MyBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button localService,intentService,boundService;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localService=(Button) findViewById(R.id.localBackGroundService);
        intentService=(Button) findViewById(R.id.intentService);
        boundService=(Button) findViewById(R.id.boundService);
        localService.setOnClickListener(this);
        intentService.setOnClickListener(this);
        boundService.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.intentService :
                intent=new Intent(this,LocalActivity.class);
                startActivity(intent);
                break;
            case R.id.localBackGroundService :
                intent=new  Intent(this,LocalAsynckService.class);
                startService(intent);
                break;
            case R.id.boundService :
                intent=new Intent(this,DistantService.class);
                startActivity(intent);
                break;
        }
    }
}
