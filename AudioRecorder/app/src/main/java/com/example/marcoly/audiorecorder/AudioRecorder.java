package com.example.marcoly.audiorecorder;

import android.media.MediaRecorder;

import java.io.IOException;

/**
 * Created by Marco Ly on 2018-03-05.
 */

public class AudioRecorder implements Runnable {
    private MediaRecorder recorder;

    @Override
    public void run() {
//        setupMediaRecorder(path);
        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupMediaRecorder(String path) {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(path);
    }
}
