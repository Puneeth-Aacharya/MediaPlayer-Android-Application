package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;  //uniform resource identifier (to identify the resource path)
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    VideoView vw;
    ArrayList<Integer> videolist = new ArrayList<>();
    int currvideo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        vw = (VideoView)findViewById(R.id.vidvw);
        vw.setMediaController(new MediaController(this)); //mediacontroller will create a default set of controls
        vw.setOnCompletionListener(this);// this method to be called when end of the video is reached

        // video name should be in lower case alphabet.
        videolist.add(R.raw.videoone); //adding vedios to Arraylist
        videolist.add(R.raw.videotwo);
        videolist.add(R.raw.videothree);
        setVideo(videolist.get(0)); //calling user defined function

    }
    public void setVideo(int id)
    {
        String uriPath
                = "android.resource://"
                + getPackageName() + "/" + id;  //e.g - android.resource://meadiaplayer/0
        Uri uri = Uri.parse(uriPath);
        vw.setVideoURI(uri);
        vw.start();
    }

    public void onCompletion(MediaPlayer mediapalyer)
    {
        AlertDialog.Builder obj = new AlertDialog.Builder(this);
        obj.setTitle("Playback Finished!");
        obj.setIcon(R.mipmap.ic_launcher);
        MyListener m = new MyListener();  //to accept the user choice
        obj.setPositiveButton("Replay", m); //listener to be invoked when this button of the dialog is pressed
        obj.setNegativeButton("Next", m);
        obj.setMessage("Want to replay or play next video?");
        obj.show();
    }

    class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which)
        {
            if (which == -1) {
                vw.seekTo(0);
                vw.start();
            }
            else {
                ++currvideo;
                if (currvideo == videolist.size())
                    currvideo = 0;
                setVideo(videolist.get(currvideo));
            }
        }
    }
}