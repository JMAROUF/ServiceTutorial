package com.example.jamal.servicestest;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by jamal on 12/03/2018.
 */

public class LocalServie extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private final static  String TAG= "IntentService";
    public LocalServie() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"LE COMPTEUR VAUT : "+intent.getIntExtra(LocalActivity.COMPTEUR,-1));
        int i=0;
        while(i<1000000000){
            i++;
        }
    }
}
