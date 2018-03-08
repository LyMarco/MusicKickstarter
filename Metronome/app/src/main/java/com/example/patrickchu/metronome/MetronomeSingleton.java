package com.example.patrickchu.metronome;

/**
 * Created by Patrick Chu on 2018-03-05.
 */

public class MetronomeSingleton {
    private static MetronomeSingleton single_instance = null;

    private SixteenBitSynthesizer player;
    private boolean flag;
    public int bpm;
    public double[] beat;
    public double[] other;

    private MetronomeSingleton() {
        this.flag = false;
    }

    public static MetronomeSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new MetronomeSingleton();
        }

        return single_instance;
    }

    public void stopMetronome() {
        if (flag == true) {
            flag = false;
            player.stopSynthesizer();
        }
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    // Keep calculating the fullSound and looping until player is stopped.
    public void play() {
        if (flag == false) {
            flag = true;
            byte[] fullSound;
            double[] waves;
            player = new SixteenBitSynthesizer();
            this.beat = this.player.getNoteWave(SixteenBitSynthesizer.Notes.Beat);
            this.other = this.player.getNoteWave(SixteenBitSynthesizer.Notes.other);

            do {
                waves = calculateMetronomeSineWaves();
                fullSound = this.player.convert16Bit(waves);
                this.player.playSound(fullSound);
            } while (flag);
        }
    }

    //8000 is the sampleRate, the sound lasts for 1000 samples. 1/8 of a second.
    //Sound is a sign wave.
    //Return the sine waves of all the beats and silence that will be played.
    public double[] calculateMetronomeSineWaves() {
        double[] waves = new double[8000 * 60];
        int upbeat = 1;
        for (int time = 0; time < (8000 * 60); time++) {
            if (time % (int) (8000 * 60 / this.bpm) == 0) {
                for (int sound = 0; sound < 1000; sound++) {
                    if (sound < 1000) {
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
