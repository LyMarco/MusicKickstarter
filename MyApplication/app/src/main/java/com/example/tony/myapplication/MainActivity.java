package com.example.tony.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public static final int NOTE_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int NOTE_MARGINS = 0;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.linearLayout);

        for(int i = 0; i < Infor.map.size(); i++) {
            createNote(i);
        }
    }

    public void add(View view) {
        Main2Activity.index = Infor.map.size();
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void noteButton(int index) {
        Intent intent = new Intent(this, Main2Activity.class);
        //Main2Activity.index = index;
        startActivity(intent);
    }

    public void createNote(int index) {
        // Get the layout and set the margins.
        LinearLayout.LayoutParams margins = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        margins.setMargins(NOTE_MARGINS, 0, NOTE_MARGINS, 0);

        Button b = new Button(this);
        String s = Infor.map.get(index);
        b.setLayoutParams(margins);
        b.setWidth(800);
        b.setHeight(150);
        b.setBackgroundColor(Color.rgb(255, 255, 255));
        b.setText(s);
        Main2Activity.index = index;

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                noteButton(Main2Activity.index);
            }
        });
        layout.addView(b, index);
    }
}

