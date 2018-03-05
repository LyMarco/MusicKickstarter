package com.example.patrickchu.metronome;

import android.widget.TextView;
import android.view.View;

/**
 * Created by Patrick Chu on 2018-02-26.
 */

public class Metronome {

    private SixteenBitSynthesizer player;
    private boolean flag;
    public int bpm;
    public double[] beat;
    public double[] other;

    public Metronome(int bpm) {
        this.bpm = bpm;
        player = new SixteenBitSynthesizer();
        this.beat = this.player.getNoteWave(SixteenBitSynthesizer.Notes.Beat);
        this.other = this.player.getNoteWave(SixteenBitSynthesizer.Notes.other);
        this.flag = true;
    }

    public void stopMetronome() {
        if (flag == true) {
            flag = false;
            player.stopSynthesizer();
        }
    }

    // Keep calculating the fullSound and looping until player is stopped.
    public void play() {
        byte[] fullSound;
        double[] waves;

        do {
            waves = calculateMetronomeSineWaves();
            fullSound = this.player.convert16Bit(waves);
            this.player.playSound(fullSound);
        } while (flag);
    }

    //Sound is a sign wave.
    //Return the sine waves of all the beats and silence that will be played.
    public double[] calculateMetronomeSineWaves() {
        double[] waves = new double[16000 * 60];
        int upbeat = 1;
        for (int time = 0; time < (16000 * 60); time++) {
            if (time % (int) (16000 * 60 / this.bpm) == 0) {
                for (int sound = 0; sound < 2000; sound++) {
                    if (sound < 2000) {
                        //The tick lasts for 1/8 of a second
                        if (upbeat % 4 == 0) {
                            waves[time] = this.other[sound];
                        } else {
                            waves[time] = this.beat[sound];
                        }
                    } else {
                        //Fill the rest of the tick with silence
                        waves[time] = 0;
                    }
                    time++;
                }
                upbeat++;
            } else {
                //Fill a second with silence if bpm is quite small
                waves[time] = 0;
            }
        }
        return waves;
    }
}