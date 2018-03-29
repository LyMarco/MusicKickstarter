package team11.csc301.musicjumpstarterapp;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MetronomeActivity extends AppCompatActivity {
    private TextView text;
    private SeekBar seekBar;
    private SeekBar upBeatBar;
    private MediaPlayer mediaPlayer;

    private int upBeat = 4;
    private int bpm = 60;
    private Thread d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
        MetronomeSingleton.getInstance().stopMetronome();

		/* UI functionality */
        text = (TextView) findViewById(R.id.textViewMetronome);
        seekBar = (SeekBar) findViewById(R.id.seekBarMetronome);
        //upBeatBar = (SeekBar) findViewById(R.id.upBeatBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                bpm = 30 + 10 * progress;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text.setText(bpm + " Beats Per Minute");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (d != null) {
                    MetronomeSingleton.getInstance().stopMetronome();
                }
            }
        });
        /*
        upBeatBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                upBeat = 1 + progress;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (d != null) {
                    MetronomeSingleton.getInstance().stopMetronome();
                }
            }
        });
        */
        MetronomeSingleton metronome = MetronomeSingleton.getInstance();
    }

    public void startMetronome(View view) {
        stopDrums();
        this.d = new Thread(new Runnable() {
            public void run() {
                MetronomeSingleton metronome = MetronomeSingleton.getInstance();
                metronome.setBpm(bpm);
                metronome.setUpbeat(upBeat);
                metronome.play();
            }
        });
        this.d.start();
    }

    public void startDrums(View view) {
        MetronomeSingleton.getInstance().stopMetronome();
        stopDrums();
        String filename = "bpm" + Integer.toString(bpm);
        int id = getResources().getIdentifier(filename, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, id);
        this.d = new Thread(new Runnable() {
            public void run() {
                mediaPlayer.start();
            }
        });
        this.d.start();
    }

    private void stopDrums(){
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    public void stopMetronome(View view) {
        MetronomeSingleton.getInstance().stopMetronome();
        stopDrums();
    }

    public void goBackFromMetronome(View view) {
        Intent intent = new Intent(this, Lyrics.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // Stops Metronome when exiting the application
    @Override
    protected void onUserLeaveHint()
    {
        MetronomeSingleton.getInstance().stopMetronome();
        super.onUserLeaveHint();
    }
}

