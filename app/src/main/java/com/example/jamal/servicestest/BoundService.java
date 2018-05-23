package com.example.jamal.servicestest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

/**
 * Created by jamal on 03/04/2018.
 */

public class BoundService extends Service {

    private static String TAG="BoundService";
    private IBinder mBinder= new MyBinder();
    private Chronometer mChronometer;


   @Override
    public void onCreate(){
       super.onCreate();
       Log.v(TAG,"in onCreate");
       mChronometer = new Chronometer(this);
       mChronometer.setBase(SystemClock.elapsedRealtime());
       mChronometer.start();

   }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public void onRebind(Intent intent){
        Log.v(TAG,"in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.v(TAG, "in onUnbind");
        return true ;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "in onDestroy");
        mChronometer.stop();
    }


    public class MyBinder extends Binder{
        BoundService getService(){

            return BoundService.this ;
        }
    }

    public String getTimestamp() {
        long elapsedMillis = SystemClock.elapsedRealtime()
                - mChronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }
}
