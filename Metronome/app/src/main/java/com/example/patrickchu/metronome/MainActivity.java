package com.example.patrickchu.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /** Called when the user taps the Metronome button */
    public void sendMetronome(View view) {
        Intent intent = new Intent(this, MetronomeActivity.class);
        startActivity(intent);
    }
}