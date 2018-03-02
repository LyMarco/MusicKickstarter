package com.example.patrickchu.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MetronomeActivity extends AppCompatActivity {

    private Metronome metronome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
    }

    /** Called when the user taps the Send button */
    public void startMetronome(View view) {
        this.metronome = new Metronome(240);
        TextView textView = findViewById(R.id.textViewMetronome);
        this.metronome.play();
        textView.setText(Integer.toBinaryString((int) (this.metronome.beat[175] * (2^16 - 1))));
    }

    public void stopMetronome(View view) {
        this.metronome.stopMetronome();
    }
}
