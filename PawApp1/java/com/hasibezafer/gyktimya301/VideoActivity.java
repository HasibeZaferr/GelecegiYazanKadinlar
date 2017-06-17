package com.hasibezafer.gyktimya301;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video=(VideoView)findViewById(R.id.video);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(video);

        Uri adres = Uri.parse("android.resource://" + getPackageName()
                + "/"
                + R.raw.animal);

        video.setMediaController(mediaController);
        video.setVideoURI(adres);
        video.start();

    }
}
