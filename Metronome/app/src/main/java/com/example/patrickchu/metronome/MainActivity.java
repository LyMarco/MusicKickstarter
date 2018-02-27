package com.example.patrickchu.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Metronome metronome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createMetronome(View view) {
        metronome = new Metronome();
        metronome.play();
    }

    public void destroyMetronome(View view) {
        metronome.stop();
        metronome = null;
    }
}
