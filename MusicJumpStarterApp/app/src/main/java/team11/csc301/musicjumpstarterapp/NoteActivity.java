package team11.csc301.musicjumpstarterapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NoteActivity extends AppCompatActivity {
    public static final int NOTE_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int NOTE_MARGINS = 0;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        layout = (LinearLayout) findViewById(R.id.linearLayout);

        HashMap<Integer, String> map = (HashMap<Integer, String>)Infor.map.clone();

        //the following steps are just rejusting Infor.map, so that its keys will be from 0 to its length-1
        Infor.map = new HashMap<Integer, String>();
        Set<Integer> keySet = map.keySet();
        Iterator<Integer> i = keySet.iterator();

            for(int a = 0; a < map.size(); a++) {
            String s = map.get(i.next());
            Infor.map.put(a, s);
            createNote(a);
        }
    }

    public void add(View view) {
        NoteActivity2.index = Infor.map.size();
        Intent intent = new Intent(this, NoteActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void noteButton(int index) {
        Intent intent = new Intent(this, NoteActivity2.class);
        NoteActivity2.index = index;
        startActivity(intent);
    }

    public void createNote(final int index) {
        // Get the layout and set the margins.
        LinearLayout.LayoutParams margins = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        margins.setMargins(NOTE_MARGINS, 0, NOTE_MARGINS, 0);

        Button b = new Button(this);
        String s = Infor.map.get(index);

        if(s.length() != 0) {

            int length = s.length() > 30 ? 30:s.length();
            String finalString = s.substring(0, length);
            int newLineIndex = finalString.indexOf('\n');

            if (newLineIndex != -1) {
                finalString = finalString.substring(0, newLineIndex);
            }

            b.setLayoutParams(margins);
            b.setWidth(800);
            b.setHeight(150);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setText(finalString);

            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    noteButton(index);
                }
            });
            layout.addView(b, index);

        }
    }
}