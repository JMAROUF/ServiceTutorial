package com.example.jamal.servicestest;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jamal on 22/05/2018.
 */

public class LocalAsynckService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Toast.makeText(getBaseContext(),"Demarrage du service ",Toast.LENGTH_LONG).show();
        new BackGroundTask().execute("https://api.myjson.com/bins/134856");
        return START_STICKY;
    }


    //travail en arriére plan
    public class BackGroundTask extends AsyncTask<String,Integer,String>{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = new OkHttpClient();
        ArrayList<Protocol> protocols = new ArrayList<Protocol>();
        // juste avant l'execution
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            protocols.add(Protocol.HTTP_1_1);
            protocols.add(Protocol.HTTP_2);
            builder.protocols(protocols);
            client = builder.build();
        }
        @Override
        protected String doInBackground(String... urls) {
            Request.Builder builder = new Request.Builder();
            builder.url(urls[0]);
            Request request = builder.build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return "erreur du telechargement ";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(getBaseContext(),"destruction du service ",Toast.LENGTH_LONG).show();
    }
}
