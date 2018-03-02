package com.example.patrickchu.metronome;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MetronomeActivity extends AppCompatActivity {

    private AudioManager audioManager;
    private SoundPool soundPool;
    private int bpm = 1000;
    private boolean flag;
    private int low;
    private int high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        this.soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        this.low = this.soundPool.load(this, R.raw.low,1);
        this.high = soundPool.load(this, R.raw.high,1);
    }

    public void startMetronome(View view) {
        new Thread(new Runnable()
        {
            public void run()
            {
                play();
                //this.metronome = new Metronome(240);
                //TextView textView = findViewById(R.id.textViewMetronome);
                //this.metronome.play();
                //textView.setText(Integer.toBinaryString((int) (this.metronome.beat[175] * (2^16 - 1))));
            }
        }).start();
    }

    public void play() {
        flag = true;
        int upbeat = 1;
        while (flag) {
            if (System.currentTimeMillis() % bpm == 0) {
                if (upbeat % 4 == 0) {
                    soundPool.play(this.high, 0.99f, 0.99f, 0, 0, 1);
                } else {
                    soundPool.play(this.low, 0.99f, 0.99f, 0, 0, 1);
                }
                upbeat++;
            }
        }
    }

    public void stop() {
        flag = false;
        soundPool.release();
        //this.metronome.stopMetronome();
    }
}
