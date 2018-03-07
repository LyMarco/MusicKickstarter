package team11.csc301.musicjumpstarterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Notes extends AppCompatActivity {
    public static final int NOTE_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int NOTE_MARGINS = 120;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        layout = findViewById(R.id.notesLayout);
    }

    /**
     * Create a note.
     *
     * @param text text of the note
     * @param index index at which the note is stored
     */
    public void createNote(String text, int index) {
        // Get the layout and set the margins.
        LinearLayout.LayoutParams margins = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        margins.setMargins(NOTE_MARGINS, 0, NOTE_MARGINS, 0);

        // Create the verse view.
        EditText newNote = new EditText(this);
        newNote.setLayoutParams(margins);
        newNote.setText(text);
        newNote.setInputType(NOTE_INPUT_TYPE);

        // Add view.
        layout.addView(newNote, index);
    }

    /**
     * Create a new verse with default text and the next numbered title.
     *
     * @param view view from which this method is called
     */
    public void createNewNote(View view) {
        createNote("Type note here.", layout.getChildCount());
    }
}
