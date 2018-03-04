package com.example.marcoly.audiorecorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Variable Declaration
    Button btnStartRecording, btnStopRecording, btnStartPlayback, btnStopPlayback;
    String path = "";
    MediaRecorder recorder;
    MediaPlayer player;

    final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 1000;
    final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views
        btnStartRecording = findViewById(R.id.btnStartRecording);
        btnStopRecording = findViewById(R.id.btnStopRecording);
        btnStartPlayback = findViewById(R.id.btnStartPlayback);
        btnStopPlayback = findViewById(R.id.btnStopPlayback);

        if(checkPermissionFromDevice()) {
            btnStartRecording.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    path = Environment.getExternalStorageDirectory()
                            .getAbsolutePath()+"/"
                            + UUID.randomUUID().toString()+"_audio_rec.3gp";
                    setupMediaRecorder();
                    try {

                        recorder.prepare();
                        recorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    btnStartPlayback.setEnabled(false);
                    btnStopPlayback.setEnabled(false);

                    Toast.makeText(MainActivity.this, "Recording...",
                            Toast.LENGTH_SHORT).show();
                }
            });

            btnStopRecording.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    recorder.stop();
                    btnStartRecording.setEnabled(true);
                    btnStopRecording.setEnabled(false);
                    btnStartPlayback.setEnabled(true);
                    btnStopPlayback.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Recording Stopped",
                            Toast.LENGTH_SHORT).show();
                }
            });

            btnStartPlayback.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    btnStartRecording.setEnabled(false);
                    btnStopRecording.setEnabled(false);
                    btnStopPlayback.setEnabled(true);

                    player = new MediaPlayer();

                    try {
                        player.setDataSource(path);
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();

                    Toast.makeText(MainActivity.this, "Playing...",
                            Toast.LENGTH_SHORT).show();
                }
            });

            btnStopRecording.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    btnStartRecording.setEnabled(true);
                    btnStopRecording.setEnabled(false);
                    btnStartPlayback.setEnabled(true);
                    btnStopPlayback.setEnabled(false);

                    if(player != null) {
                        player.stop();
                        player.release();
                        setupMediaRecorder();
                    }
                }

            });
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        }
    }

    private void setupMediaRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(path);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Mic Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Mic Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Save Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Save Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private boolean checkPermissionFromDevice() {
        boolean record_audio = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean store_file = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return record_audio && store_file;
    }
}
