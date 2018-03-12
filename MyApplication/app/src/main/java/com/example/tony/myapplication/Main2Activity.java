package com.example.tony.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    public static int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        int size = Infor.map.size();
        if(index < size && index > -1) {
            String s = Infor.map.get(index);
            EditText editText1 = (EditText) findViewById(R.id.editText);
            editText1.setText(s);
        }
    }

    public void back(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String s = editText.getText().toString();
        Infor.map.put(index, s);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
