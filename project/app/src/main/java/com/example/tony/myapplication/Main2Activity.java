package com.example.tony.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Infor.init();
        EditText editText1 = (EditText) findViewById(R.id.editText5);
        EditText editText2 = (EditText) findViewById(R.id.editText6);
        EditText editText3 = (EditText) findViewById(R.id.editText7);
        EditText editText4 = (EditText) findViewById(R.id.editText8);
        EditText editText5 = (EditText) findViewById(R.id.editText9);
        EditText editText6 = (EditText) findViewById(R.id.editText10);
        EditText editText7 = (EditText) findViewById(R.id.editText11);
        EditText editText8 = (EditText) findViewById(R.id.editText12);

        editText1.setText(Infor.infor.get(1));
        editText2.setText(Infor.infor.get(2));
        editText3.setText(Infor.infor.get(3));
        editText4.setText(Infor.infor.get(4));
        editText5.setText(Infor.infor.get(5));
        editText6.setText(Infor.infor.get(6));
        editText7.setText(Infor.infor.get(7));
        editText8.setText(Infor.infor.get(8));
    }

    /** Called when the user taps the back button */
    public void Save(View view) {
        EditText editText1 = (EditText) findViewById(R.id.editText5);
        EditText editText2 = (EditText) findViewById(R.id.editText6);
        EditText editText3 = (EditText) findViewById(R.id.editText7);
        EditText editText4 = (EditText) findViewById(R.id.editText8);
        EditText editText5 = (EditText) findViewById(R.id.editText9);
        EditText editText6 = (EditText) findViewById(R.id.editText10);
        EditText editText7 = (EditText) findViewById(R.id.editText11);
        EditText editText8 = (EditText) findViewById(R.id.editText12);

        String message1 = editText1.getText().toString();
        String message2 = editText2.getText().toString();
        String message3 = editText3.getText().toString();
        String message4 = editText4.getText().toString();
        String message5 = editText5.getText().toString();
        String message6 = editText6.getText().toString();
        String message7 = editText7.getText().toString();
        String message8 = editText8.getText().toString();

        Infor.infor.put(1, message1);
        Infor.infor.put(2, message2);
        Infor.infor.put(3, message3);
        Infor.infor.put(4, message4);
        Infor.infor.put(5, message5);
        Infor.infor.put(6, message6);
        Infor.infor.put(7, message7);
        Infor.infor.put(8, message8);
    }
}