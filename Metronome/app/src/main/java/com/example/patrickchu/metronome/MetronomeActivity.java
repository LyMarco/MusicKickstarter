package com.example.patrickchu.metronome;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MetronomeActivity extends AppCompatActivity {

    private TextView text;
    private SeekBar seekBar;
    private int bpm = 60;

    private AudioManager audioManager;
    private SoundPool soundPool;
    private int low;
    private int high;
    private Thread d;
    private boolean thread;

    private Metronome metronome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        //audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //this.soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        //this.low = this.soundPool.load(this, R.raw.low,1);
        //this.high = soundPool.load(this, R.raw.high,1);
        //this.thread = false;
        text = (TextView) findViewById(R.id.textViewMetronome);
        seekBar = (SeekBar) findViewById(R.id.seekBarMetronome);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                bpm = 20 + 20 * progress;
            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text.setText(bpm + " Beats Per Minute");
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(d != null) {
                    d.interrupt();
                    metronome.stopMetronome();
                    thread = false;
                }
            }
        });
    }

    public void startMetronome(View view) {
        if (this.thread == false) {
            this.thread = true;
            this.d = new Thread(new Runnable() {
                public void run() {
                    //Trial * 1
                    //play();
                    //Trial * 2 Accurate but sounds robotic
                    metronome = new Metronome(bpm);
                    metronome.play();
                }
            });
            this.d.start();
        }

         /* TODO: Get out and back into instance */
        // setContentView(R.layout.activity_main);
    }


    /*
        Trial * 1 Not accurate but sounds good
     */
    public void play() {
        int upbeat = 1;
        while (!Thread.currentThread().isInterrupted()) {
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

    public void stopMetronome(View view) {
        this.d.interrupt();
        this.metronome.stopMetronome();
        this.thread = false;
    }
}
