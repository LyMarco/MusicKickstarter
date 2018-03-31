package team11.csc301.musicjumpstarterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NoteActivity2 extends AppCompatActivity {

    public static int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note2);
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
        if(s.length() != 0)
            Infor.map.put(index, s);

        Intent intent = new Intent(this, NoteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void delete(View view) {
        Infor.map.remove(index);

        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
}

