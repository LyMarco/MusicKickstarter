package com.example.tony.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the back button */

    public void add(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

       // Infor.notes.add("hello world");
       /* for(String s: Infor.notes) {
            Button button = new Button(this);
            //button.setText(s,0,s.length()-1);
            button.setText(s);
            button.setX(150);
            button.setY(900);
            button.setHeight(800);
            button.setWidth(800);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(this, Main2Activity.class);
                    // startActivity(intent);
                }
            });
            button.setVisibility(View.VISIBLE);
            button.invalidate();
            //button.setLayerPaint(MainActivity.class);
            //button.setOnClickListener();
        }*/
    }
}

