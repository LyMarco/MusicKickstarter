package com.example.patrickchu.metronome;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import java.nio.ByteBuffer;

/**
 * Created by PC on 2018-02-02.
 */

public class SixteenBitSynthesizer {
    //https://pages.mtu.edu/~suits/notefreqs.html
    //The double values are the frequency of the notes
    public enum Notes {
        Beat(220),
        other(330),
        A(440),
        B(493.88),
        C(523.25),
        D(587.33),
        E(659.25),
        F(698.46),
        G(783.99);

        private final double id;
        Notes(double id) {this.id = id;}
        public double getValue() {return id;}
    }

    private AudioTrack audio;

    public SixteenBitSynthesizer() {
        audio = new AudioTrack(AudioManager.STREAM_MUSIC,  16000,
                AudioFormat.CHANNEL_CONFIGURATION_STEREO,
                AudioFormat.ENCODING_PCM_16BIT,16000, AudioTrack.MODE_STREAM);

        audio.play();
    }

    public void playSound(byte[] sound) {
        audio.write(sound, 0, sound.length);
    }

    public void stopSynthesizer() {
        audio.stop();
        audio.release();
    }

    //https://en.wikipedia.org/wiki/Pulse-code_modulation (MODULATION section)
    // Audio can be represented as a sine wave,
    // and this sine wave can be represented by an array of doubles
    //
    // https://courses.cs.washington.edu/courses/cse143/18wi/homework/ass2/audio.html
    // Return the sine wave for a given note
    // 1000 is an eight of 8000, resulting in a note that is 1/8 of a second.
    public double[] getNoteWave(Notes note) {
        double[] wave = new double[2000];
        for (int i = 0; i < 2000; i++) {
            wave[i] = Math.sin(2 * Math.PI * i * note.getValue() / 16000);
        }
        return wave;
    }

    // https://en.wikipedia.org/wiki/Pulse-code_modulation
    // Return an array of floats;
    //
    // Return an array of 16 bits, representing the sound in PCM values.
    // I would be working with a float by doing the >> 16 and >>32 conversions but I don't
    // have access to PCM_FLOAT encoding as that is android 2.1+ so 16bit is the best note
    // synthesizer I have access too.
    public byte[] convert16Bit(double[] waves) {
        int i = 0;
        byte[] sound = new byte[2 * waves.length];

        for (double value : waves) {
            int bits = (int) ((value * (2^16 - 1)));
            // in 16 bit wav PCM, first byte is the low order byte
            sound[i++] = (byte) (bits & 0xff);
            sound[i++] = (byte) ((bits >> 8) & 0xff);
        }

        return sound;
    }
}
