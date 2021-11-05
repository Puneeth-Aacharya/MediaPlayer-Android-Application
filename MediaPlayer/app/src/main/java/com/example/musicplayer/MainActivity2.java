package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    ImageButton btnPlay,btnFwd,btnBwd;
    ImageView img;
    SeekBar seekbar;
    Handler mHandler=new Handler();
    Runnable mRunnable;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        img=findViewById(R.id.img);
        btnPlay=findViewById(R.id.play);
        btnFwd=findViewById(R.id.fwd);
        btnBwd=findViewById(R.id.bwd);

        btnPlay.setImageResource(R.drawable.play);
        btnFwd.setImageResource(R.drawable.forward);
        btnBwd.setImageResource(R.drawable.backward);
        img.setImageResource(R.drawable.music);

        mediaPlayer=MediaPlayer.create(this,R.raw.song2);
        seekbar=findViewById(R.id.seekBar);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer.isPlaying())
                {
                    mediaPlayer.start();
                    getAudioStats();

                    btnPlay.setImageResource(R.drawable.pause);
                    updateSeekBar();
                }
                else
                {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }
            }
        });

        seekbar.setOnTouchListener((v, event)->{
            SeekBar s=(SeekBar) v;
            int position=(mediaPlayer.getDuration()/100) * s.getProgress();
            mediaPlayer.seekTo(position);
            return false;
        });

        btnFwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.getDuration()>mediaPlayer.getCurrentPosition()+10000)
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
                    updateSeekBar();
                }
            }
        });
        btnBwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.getCurrentPosition() >10000)
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
                    updateSeekBar();
                    getAudioStats();
                }
            }
        });
    }
    private void updateSeekBar()
    {
        if(mediaPlayer.isPlaying()){
            seekbar.setProgress((int)((float)mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration()*100));
            mHandler.postDelayed(updater,1000);
        }
    }
    protected void getAudioStats(){
        int duration=mediaPlayer.getDuration()/1000;
        int due=(mediaPlayer.getDuration()-mediaPlayer.getCurrentPosition())/1000;

    }
    private Runnable updater=new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };

    private void stopPlaying()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
            Toast.makeText(this,"Stop Playing",Toast.LENGTH_SHORT).show();
            if(mHandler!=null)
            {
                mHandler.removeCallbacks(mRunnable);
            }
        }
    }
}