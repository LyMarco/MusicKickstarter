package com.example.patrickchu.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Metronome metronome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void createMetronome() {
        metronome = new Metronome();
        metronome.play();
    }

    protected void destroyMetronome() {
        metronome.stop();
        metronome = null;
    }
}
