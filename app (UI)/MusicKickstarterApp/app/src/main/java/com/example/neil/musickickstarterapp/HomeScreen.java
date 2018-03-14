package com.example.neil.musickickstarterapp;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class HomeScreen extends Activity {

    private boolean paused = true;

    public void buttonPressed(View view) {

        ImageButton button = (ImageButton) view;
        int icon;

        if (paused) {
            paused = false;
            icon = R.drawable.pause;
        }
            else {
                paused = true;
                icon = R.drawable.play;
            }

            button.setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(), icon));


        }
    public void buttonPressed2(View view) {

        ImageButton button = (ImageButton) view;
        int icon;

        if (paused) {
            paused = false;
            icon = R.drawable.record;
        }
        else {
            paused = true;
            icon = R.drawable.record_stop;
        }

        button.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_screen);
    }
}
