package com.example.patrickchu.metronome;

import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by Patrick Chu on 2018-02-26.
 */

public class Metronome {
    private SoundPool soundPool;
    // Default bpm
    private int bpm = 1000;
    private boolean flag;
    private int sound;

    public Metronome () {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load("tick.wav", 1);
    }

    public void play() {
        flag = true;
        while (flag) {
            if (System.currentTimeMillis() % bpm == 0) {
                soundPool.play(sound,0.99f, 0.99f, 0, 0, 1);
            }
        }
    }

    public void stop() {
        flag = false;
        soundPool.release();
    }
}
