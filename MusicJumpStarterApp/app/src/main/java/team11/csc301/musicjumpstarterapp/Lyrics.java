package team11.csc301.musicjumpstarterapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Lyrics extends AppCompatActivity {
    // My Permissions
    private final int PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 100;
    private final int PERMISSIONS_REQUEST_RECORD_AUDIO = 200;
    // Finals needed for Verses
    public static final int VERSE_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int VERSE_TITLE_INPUT_TYPE = InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
    public static final int VERSE_MARGINS = 120;
    // Activity Layour
    LinearLayout layout;
    // Variables for Audio Recording and Playback
    private boolean paused = true;
    private String audioPath = "";
    private MediaRecorder recorder;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        layout = findViewById(R.id.lyricLayout);


        // Check that you have the proper recording and saving permissions
        if (!checkPermissionFromDevice()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RECORD_AUDIO);
        }

        // Initialize the verses layout
        initVerses();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Need to save text from each verse to file along with their titles in a format so that we
        // can keep the ordering of the verses.
        EditText title, verse;
        String titleText, verseText;
        for (int i = 0; i < layout.getChildCount(); i += 2) {
            title = (EditText) layout.getChildAt(i);
            verse = (EditText) layout.getChildAt(i + 1);
            titleText = title.getText().toString();
            verseText = verse.getText().toString();
            /* TODO: save strings 'titleText' and 'verseText' while keeping the ordering. */
        }
    }

    /**
     * Initialize the activity by creating all saved verses and storing the ID's of the views for
     * each of these verses and their titles.
     */
    public void initVerses() {
        int verseCount = getVerseCountFromFile();
        for (int i = 0; i < verseCount; i++) {
            createVerse(getTextFromFile(i), getTitleFromFile(i), i);
        }

        //Test Lyrics Suggestions
        String suggestions = LyricsSuggestion.GetSuggestions(this, "tomato");
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
     * @param text  text of the verse
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
     * @param id   verse to delete
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
                Integer.parseInt(title.getText().toString().substring(0, 1));
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
        // Temp
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
        return "Write verse here.";
    }

    /**
     * Get the title of verse v from file.
     *
     * @param v the verse to get from file
     * @return title of the verse as a String
     */
    public String getTitleFromFile(int v) {
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

    /*AUDIO RECORDING SECTION OF MAIN ACTIVITY*/

    public void playButtonPressed(View view) {
        ImageButton button = (ImageButton) view;
        int icon;
        paused = !paused;
        if (!paused) {
            icon = R.drawable.pause;
            player = new MediaPlayer();
            runOnThread(new Runnable() {
                public void run() {
                    startPlayback();
                }
            }, "Playing...");
        } else {
            icon = R.drawable.play;
            runOnThread(new Runnable() {
                public void run() {
                    stopPlayback();
                }
            }, "");
        }
        button.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));
    }

    public void recButtonPressed(View view) {
        ImageButton button = (ImageButton) view;
        int icon;
        paused = !paused;
        if (!paused) {
            icon = R.drawable.record;
            // TODO: Make audio path changeable rather than setting randoms
            audioPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath()+"/"
                    + UUID.randomUUID().toString()+"_audio_rec.3gp";
            setupMediaRecorder(audioPath);
            runOnThread(new Runnable() {
                public void run() {
                    stopRecording();
                }
            }, "Recording Stopped");
        } else {
            icon = R.drawable.record_stop;
            runOnThread(new Runnable() {
                public void run() {
                    startRecording();
                }
            }, "Recording...");
        }
        button.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));

    }

    private void setupMediaRecorder(String filepath) {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(filepath);
    }

    private void runOnThread(final Runnable func, String message) {
        new Thread(func).start();
        if (!message.equals(""))
            Toast.makeText(Lyrics.this, message, Toast.LENGTH_SHORT).show();
    }

    private void startRecording() {
        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        recorder.stop();
        recorder.reset();
    }

    private void startPlayback(){
        try {
            player.setDataSource(audioPath);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    private void stopPlayback(){
        if(player != null) {
            player.stop();
            player.release();
//                    setupMediaRecorder(path);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Mic Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Mic Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Save Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Save Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private boolean checkPermissionFromDevice() {
        boolean record_audio = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean store_file = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return record_audio && store_file;
    }

    /*END OF AUDIO RECORDING SECTION OF MAIN ACTIVITY*/
}
