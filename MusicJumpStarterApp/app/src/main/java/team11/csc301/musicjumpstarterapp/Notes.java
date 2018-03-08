package team11.csc301.musicjumpstarterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Notes extends AppCompatActivity {
    public static final int NOTE_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int NOTE_MARGINS = 120;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        layout = findViewById(R.id.notesLayout);
        int noteCount = getNoteCountFromFile();
        Log.d("Note loading", Lyrics.current.getSongname());
        for (int i = 0; i < noteCount; i++) {
            createNote(getTextFromFile(i), i);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EditText note;
        String noteText;
        ArrayList<String> notes = new ArrayList<String>();
        LinearLayout noteLayout = findViewById(R.id.notesLayout);
        if (noteLayout != null) {
            for (int i = 0; i < noteLayout.getChildCount(); i += 1) {
                note = (EditText) noteLayout.getChildAt(i);
                noteText = note.getText().toString();
                notes.add(noteText);
            }
        }
        Lyrics.current.setNotes(notes);
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

    /**
     * Get the number of notes that should be displayed by this activity from file.
     *
     * @return number of notes
     */
    public int getNoteCountFromFile() {
        int length = Lyrics.current.getNotes().size();
        if (length > 0) {
            return length;
        }
        return 0;
    }

    /**
     * Get the text of note n from file.
     *
     * @param n the note to get from file
     * @return text of the note as a String
     */
    public String getTextFromFile(int n) {
        if (n < Lyrics.current.getNotes().size()) {
            return Lyrics.current.getNotes().get(n);
        }
        return "Type note here";
    }
}


