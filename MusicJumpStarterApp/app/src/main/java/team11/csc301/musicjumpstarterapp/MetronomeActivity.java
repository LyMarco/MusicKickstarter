package team11.csc301.musicjumpstarterapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MetronomeActivity extends AppCompatActivity {
    /* All variables between 1 and 2 */
    private TextView text;
    private SeekBar seekBar;
    private int bpm = 60;

    /* Trial 1 Variables */
    // private AudioManager audioManager;
    // private SoundPool soundPool;
    // private int low;
    // private int high;
    private Thread d;
    // private boolean thread;

	/* Trial 2 Variables */
    // private Metronome metronome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
	MetronomeSingleton.getInstance().stopMetronome();    

		/* Trial 1 Variables */
        //audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //this.soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        //this.low = this.soundPool.load(this, R.raw.low,1);
        //this.high = soundPool.load(this, R.raw.high,1);
        //this.thread = false;

		/* UI functionality */
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
                    MetronomeSingleton.getInstance().stopMetronome();
                }
            }
        });

        MetronomeSingleton metronome = MetronomeSingleton.getInstance();
    }

    public void startMetronome(View view) {
        this.d = new Thread(new Runnable() {
            public void run() {
                //Trial * 1
                //play();

                //Trial * 2 Accurate but sounds robotic
                MetronomeSingleton metronome = MetronomeSingleton.getInstance();
                metronome.setBpm(bpm);
                metronome.play();
            }
        });
        this.d.start();
    }


    /* Trial * 1 Not accurate but sounds good */
//    public void play() {
//        int upbeat = 1;
//        while (!Thread.currentThread().isInterrupted()) {
//            if (System.currentTimeMillis() % bpm == 0) {
//                if (upbeat % 4 == 0) {
//                    soundPool.play(this.high, 0.99f, 0.99f, 0, 0, 1);
//                } else {
//                    soundPool.play(this.low, 0.99f, 0.99f, 0, 0, 1);
//                }
//                upbeat++;
//            }
//        }
//    }

    public void stopMetronome(View view) {
        //this.d.interrupt();
        MetronomeSingleton.getInstance().stopMetronome();
        //this.thread = false;
    }

    public void goBackFromMetronome(View view) {
        Intent intent = new Intent(this, Lyrics.class);
        startActivity(intent);
    }
}

