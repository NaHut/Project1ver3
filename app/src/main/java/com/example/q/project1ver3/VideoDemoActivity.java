package com.example.q.project1ver3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import android.widget.MediaController;

public class VideoDemoActivity extends Activity {
    //private static final String path = "https://video.xx.fbcdn.net/v/t42.1790-2/21925027_1582088808500778_300351164703047680_n.mp4?_nc_cat=0&efg=eyJybHIiOjcwOSwicmxhIjo1MTIsInZlbmNvZGVfdGFnIjoic3ZlX3NkIn0%3D&rl=709&vabr=394&oh=41a4a8b8a6a59e8f62f17c458bb2f4f8&oe=5B3C9A8C";
    private static final String path = "android.resource://com.example.q.project1ver3"+R.raw.hwvid;
    private VideoView video;
    private MediaController ctlr;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.videoview);

        video = (VideoView) findViewById(R.id.videoView);
        //video.setVideoPath(path);
        video.setVideoURI(Uri.parse("android.resource://com.example.q.project1ver3/"+R.raw.hwvid));

        ctlr = new MediaController(this);
        ctlr.setMediaPlayer(video);
        ctlr.hide();
        video.setMediaController(ctlr);
        video.requestFocus();
        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                Intent intent = new Intent(VideoDemoActivity.this, worldcupActivity.class);
                startActivity(intent);
                VideoDemoActivity.this.finish();;
            }
        });
    }
}
