package team11.csc301.musicjumpstarterapp;


import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Lyrics extends AppCompatActivity {
    public static final int VERSE_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int VERSE_TITLE_INPUT_TYPE = InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
    public static final int VERSE_MARGINS = 120;
    public static  Song current = null;
    public static HashSet<User> users = new HashSet<User>();
    public static String sPath;


    LinearLayout layout;
    private boolean paused = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);


        layout = findViewById(R.id.lyricLayout);
        sPath = getApplicationContext().getFilesDir().getAbsolutePath();
        Log.d("Path",sPath);
        init();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Need to save text from each verse to file along with their titles in a format so that we
        // can keep the ordering of the verses.
        EditText title, verse;
        String titleText, verseText;
        ArrayList<String> verses = new ArrayList<String>();
        ArrayList<String> titles = new ArrayList<String>();
        for (int i = 0; i < layout.getChildCount(); i += 2) {
            title = (EditText) layout.getChildAt(i);
            verse = (EditText) layout.getChildAt(i + 1);
            titleText = title.getText().toString();
            verseText = verse.getText().toString();
            /* TODO: save strings 'titleText' and 'verseText' while keeping the ordering. */
            verses.add(verseText);
            titles.add(titleText);
            Log.d("onStop:", verseText);
        }
        Log.d("onStop:", "Set");
        Log.d("onStop:", verses.get(0));
        current.setVerses(verses);
        current.setTitles(titles);
        try {
            SerializationBase.saveStop(users, current.getUser().getUsername(), current.getSongname());
        } catch (Exception e){
            Log.v("Save_Stop Error : ",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initialize the activity by creating all saved verses and storing the ID's of the views for
     * each of these verses and their titles.
     */
    public void init() {
        // Dessrialize test
        Log.d("Init:", "start");
       if (! new File(sPath + "/users.ser").isFile()) {
            User user = new User("Default");
            users.add(user);
            current = new Song(user, "Default");
            user.addSong(current);
            Log.d("Not_Found:", sPath + "/users.ser");
        } else {
            Log.d("Read: ", "Start read");
            HashSet<User> users = null;
            String usersfile = "/users.ser";
            String userfile = "/user.ser";
            String songfile = "/song.ser";
            users = SerializationBase.genericLoad(usersfile, new HashSet<User>());
            String username = (String) SerializationBase.loadObject(userfile);
            Log.d("Read username:", username);
            String songname = (String) SerializationBase.loadObject(songfile);
            Log.d("Read songname", songname);
            Log.d("Read user", "");
           for (User user : users) {
               Log.d("User", user.getUsername());
               if (user.getUsername().equals(username)) {
                   Log.d("User", user.getSong(songname).getSongname());
                   current = user.getSong(songname);
               }
           }
        }
        //
        int verseCount = getVerseCountFromFile();
        for (int i = 0; i < verseCount; i++) {
            createVerse(getTextFromFile(i), getTitleFromFile(i), i);
        }

        //Test Lyrics Suggestions
        String suggestions = LyricsSuggestion.GetSuggestions(this,"tomato");
    }


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
        } else {
            paused = true;
            icon = R.drawable.record_stop;
        }
        button.setImageDrawable(
        ContextCompat.getDrawable(getApplicationContext(), icon));
    }

    public void goToNotes(View view) {
        Intent intent = new Intent(Lyrics.this, Notes.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
  
    /**
     * Create a verse view along along with its corresponding title view and store their ID's.
     *
     * @param text text of the verse
     * @param title title of the verse
     * @param index index at which the verse and its title are stored
     */
    public void createVerse(String text, String title, int index) {
        // Get the layout and set the margins.
        LinearLayout.LayoutParams margins = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        margins.setMargins(VERSE_MARGINS, 0, VERSE_MARGINS, 0);

        // Create the title view.
        EditText verseTitle = new EditText(this);
        verseTitle.setLayoutParams(margins);
        verseTitle.setInputType(VERSE_TITLE_INPUT_TYPE);
        verseTitle.setText(title);
        verseTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                updateVerseTitles();
            }
        });

        // Create the verse view.
        EditText newVerse = new EditText(this);
        newVerse.setLayoutParams(margins);
        newVerse.setText(text);
        newVerse.setInputType(VERSE_INPUT_TYPE);
        newVerse.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                updateVerseTitles();
            }
        });

        // Add and store views.
        layout.addView(verseTitle);
        layout.addView(newVerse);
    }

    /**
     * Create a new verse with default text and the next numbered title.
     *
     * @param view view from which this method is called
     */
    public void createNewVerse(View view) {
        createVerse("Type verse here.", layout.getChildCount() + ".", layout.getChildCount());
        updateVerseTitles();
    }

    /**
     * Delete the given verse from the layout and no longer keep track of it.
     *
     * @param view view from which this method is called
     * @param id verse to delete
     */
    public void deleteVerse(View view, int id) {
        // Not implemented.
    }

    /**
     * Update the verse titles so that they are in a valid order.
     */
    public void updateVerseTitles() {
        EditText title;
        int nextVerseNum = 1;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lyricLayout);
        for (int i = 0; i < linearLayout.getChildCount(); i += 2) {
            title = (EditText) linearLayout.getChildAt(i);
            try {
                Integer.parseInt(title.getText().toString().substring(0,1));
                title.setText(nextVerseNum + ".");
                nextVerseNum++;
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    /**
     * Get the number of verses that should be displayed by this activity from file.
     *
     * @return number of verses
     */
    public int getVerseCountFromFile() {
        int length = current.getVerses().size();
        if (length > 1) {
            return length;
        }
        return 3;
    }

    /**
     * Get the text of verse v from file.
     *
     * @param v the verse to get from file
     * @return text of the verse as a String
     */
    public String getTextFromFile(int v) {
        // Temp
        if (v < current.getVerses().size()) {
            return current.getVerses().get(v);
        }
        //
        return "Write verse here.";
    }

    /**
     * Get the title of verse v from file.
     *
     * @param v the verse to get from file
     * @return title of the verse as a String
     */
    public String getTitleFromFile(int v) {
        if (v < current.getTitles().size()) {
            return current.getTitles().get(v);
        }
        // Temp
        if (v == 0) {
            return "1.";
        } else if (v == 1) {
            return "Ch.";
        } else {
            return v + ".";
        }
    }
  
  
    /** Called when the user taps the Metronome button */
    public void sendMetronome(View view) {
        Intent intent = new Intent(this, MetronomeActivity.class);
        startActivity(intent);
    }
}
