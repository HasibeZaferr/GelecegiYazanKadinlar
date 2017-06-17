package com.hasibezafer.gyktimya301;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimalActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnVideo;
    Button btnMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(this);
        btnMaps = (Button) findViewById(R.id.btnMaps);
        btnMaps.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view == btnVideo) {
            Intent intent = new Intent(getApplicationContext(),VideoActivity.class);
            startActivity(intent);
        }

        if(view == btnMaps) {
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intent);
        }
    }
}
