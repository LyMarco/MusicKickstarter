package com.example.tony.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NotesActivity extends AppCompatActivity {
    int curKey = -1;
    public static final String EXTRA_MESSAGE = "com.example.tony.myapplication";

/*    MainActivity(NotesActivity notes, int key) {
        this.notes = notes;
        this.curKey = key;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the back button */
    public void backAndSave(View view) {
        Intent intent = new Intent(this, NotesActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
       // String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void delete(View view) {
        if(curKey != -1) {
            //notes.contents.remove(curKey);
        } else {
            Intent intent = new Intent(this, NotesActivity.class);
            startActivity(intent);
        }
        //Serialization code here
    }
}