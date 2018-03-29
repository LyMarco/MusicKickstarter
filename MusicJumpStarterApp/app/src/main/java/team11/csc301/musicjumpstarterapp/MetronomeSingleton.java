package team11.csc301.musicjumpstarterapp;

/**
 * Created by Patrick Chu on 2018-03-05.
 */

public class MetronomeSingleton {
    private static MetronomeSingleton single_instance = null;

    private SixteenBitSynthesizer player;
    private boolean flag;
    public int upbeat;
    public int bpm;
    private int waiting;
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
        this.bpm = Math.round((8000 * 60) / bpm);
    }
    public void setUpbeat(int upbeat) {
        this.upbeat = upbeat;
    }

    // Keep calculating the fullSound and looping until player is stopped.
    public void play() {
        if (flag == false) {
            flag = true;
            byte[] fullSound;
            double[] waves = new double[8000];
            int sound = 0;
            int currentbeat = 1;
            this.waiting = 0;

            player = new SixteenBitSynthesizer();
            this.beat = this.player.getNoteWave(SixteenBitSynthesizer.Notes.Beat);
            this.other = this.player.getNoteWave(SixteenBitSynthesizer.Notes.other);

            do {
                for (int time = 0; time < 8000; time++) {
                    if (waiting == 0) {
                        if (sound == 1000) {
                            sound = 0;
                            waiting = bpm;
                            currentbeat++;
                        }

                        if (currentbeat % upbeat == 0) {
                            waves[time] = this.other[sound];
                        } else {
                            waves[time] = this.beat[sound];
                        }

                        sound++;
                    } else {
                        waves[time] = 0;
                        waiting--;
                    }
                }

                fullSound = this.player.convert16Bit(waves);
                this.player.playSound(fullSound);
            } while (flag);
        }
    }
}