package com.example.ekzamenvideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGE = "com.example.ekzamenvideos.EXTRA_MESSAGE";

    String[] videos = {"https://archive.org/download/charlie_chaplin_film_fest/charlie_chaplin_film_fest_512kb.mp4",
            "https://archive.org/download/CC_1914_08_31_TheGoodforNothing/CC_1914_08_31_TheGoodforNothing_512kb.mp4",
            "https://archive.org/download/charlie_chaplin_film_fest/charlie_chaplin_film_fest_512kb.mp4",
            "https://archive.org/download/disorder_in_the_court/disorder_in_the_court_512kb.mp4",
    "https://archive.org/download/MushAndMilk/MushnMilk_512kb.mp4"};
    Button btnPause;
    Button btnPlay;
    Button btnStop;
    Button btnLike;
    Button btnInter;
    Button btnLikes;
    EditText viborvidos;
    VideoView videoView;
    String vidosid;
    String videoAddress;
    Intent i = new Intent(MainActivity.this, MainActivity2.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent o = getIntent();
        String kols = o.getStringExtra(MainActivity2.EXTRA_MESSAGES);
        videoView = (VideoView) findViewById(R.id.videoView);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnLike = (Button) findViewById(R.id.btnlike);
        btnInter = (Button) findViewById(R.id.btnInter);
        btnLikes = (Button) findViewById(R.id.btnlikes);
        viborvidos = (EditText) findViewById(R.id.textView);
        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnLike.setOnClickListener(this);
        btnInter.setOnClickListener(this);
        btnLikes.setOnClickListener(this);

        videoAddress = "0";
        videoAddress = kols;
        Uri vidiosUri = Uri.parse(videoAddress);
        videoView.setVideoURI(vidiosUri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnPause:
                VideoPause();
                break;
            case R.id.btnPlay:
                VideoPlay();
                break;
            case R.id.btnStop:
                VideoStop();
                break;
            case R.id.btnlike:
                i.putExtra(EXTRA_MESSAGE, videoAddress);
                break;
            case R.id.btnInter:
                vidosid = viborvidos.getText().toString();
                int vidid = Integer.parseInt(vidosid);
                videoAddress = videos[vidid];
                break;
            case R.id.btnlikes:
                startActivity(i);
                break;
        }
    }

    private void VideoStop() {
        videoView.stopPlayback();
        videoView.resume();
    }

    private void VideoPlay() {
        videoView.start();
    }

    private void VideoPause() {
        videoView.pause();
    }
}