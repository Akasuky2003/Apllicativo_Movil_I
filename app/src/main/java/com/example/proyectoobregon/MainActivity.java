package com.example.proyectoobregon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    /*VideoView vvFondo;
    MediaPlayer mMediaPlayer;
    int mPositionVideo;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ImagenFondo = findViewById(R.id.imageView);

        //TimerTask
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent x = new Intent(
                        MainActivity.this, MenuPrincipalActivity.class
                );
                startActivity(x);
                finish();
            }
        };
        Glide.with(getBaseContext()).load(R.drawable.fondovideomaindos).into(ImagenFondo);

        /*
        FondoVideomain
        vvFondo = findViewById(R.id.vvFondo);
        Uri uri = Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.raw.fondovideomaindos) ;

        vvFondo.setVideoURI(uri);
        vvFondo.start();
        vvFondo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                mMediaPlayer.setLooping(true);

                if (mPositionVideo != 0){
                    mMediaPlayer.seekTo(mPositionVideo);
                    mMediaPlayer.start();
                }

            }
        });

        Timer tiempo = new Timer();
        tiempo.schedule(tarea,10200);*/

        /*FondoVideomain2*/
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,2240);


    }

    /*
    @Override
    protected void onPause() {
        super.onPause();
        mPositionVideo = mMediaPlayer.getCurrentPosition();
        vvFondo.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vvFondo.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMediaPlayer.release();
        mMediaPlayer = null;
    }*/
}