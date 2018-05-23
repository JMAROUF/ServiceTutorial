package com.example.jamal.servicestest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocalActivity extends AppCompatActivity {

    private  int compteur=0;
    protected final static String COMPTEUR="COMPTEUR";
    Button compteurButton ;
    TextView textCompteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        textCompteur= (TextView) findViewById(R.id.compteurTextView);
        compteurButton=(Button) findViewById(R.id.incrementButtton);
        compteurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocalActivity.this,LocalServie.class);
                i.putExtra(COMPTEUR,compteur);
                compteur++;
                textCompteur.setText(""+compteur);
                startService(i);

            }
        });
    }
}
