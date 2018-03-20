package team11.csc301.musicjumpstarterapp;

// Manifest Import
import android.Manifest;
// Support Imports
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
// os Imports
import android.os.Environment;
import android.os.Bundle;
// Content and Widget Imports
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.ImageButton;
import android.widget.EditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
// View imports
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
// Layout Imports
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
// Text, Logs, Views Imports
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
// Data Structures Imports
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
// Media Imports
import android.media.MediaPlayer;
import android.media.MediaRecorder;
// IO Imports
import android.text.InputType;
import java.io.File;
// Imports for saving audio
import team11.csc301.musicjumpstarterapp.SaveRecDialogFragment.SaveRecDialogListener;

public class Lyrics extends AppCompatActivity implements SaveRecDialogListener {
    // Finals for requesting Recording Permissions
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    // Finals needed for Verses
    public static final int VERSE_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int VERSE_TITLE_INPUT_TYPE = InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
    public static final int VERSE_MARGINS = 120;
    // Activity Layout
    LinearLayout layout;
    //
    public static Song current = null;
    public static HashSet<Song> songs = new HashSet<Song>();
    public static String sPath;
    // Variables needed for Audio handling
    private boolean playing = false;
    private boolean recording = false;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private String audioPath;
    private int verseNumber;
    private int takeNumber;
    // General Activity Variables
    private FragmentManager fragManager;
    private FileOutputStream audioOutStream;
    private File audioOutFile;
    private String currentVerse;
//    private String songPath;

    private RecyclerView horizontal_recycler_view_suggestions;
    private ArrayList<String> Suggestions;
    private HorizontalAdapter horizontalAdapter;

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        private List<String> horizontalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.txtView);

            }
        }


        public HorizontalAdapter(List<String> horizontalList) {
            this.horizontalList = horizontalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizontal_item_view, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.txtView.setText(horizontalList.get(position));

            holder.txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Lyrics.this,holder.txtView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        layout = findViewById(R.id.lyricLayout);
        fragManager =  getSupportFragmentManager();

        audioPath = "";
        verseNumber = 1;
//        takeNumber = 0;
      
        horizontal_recycler_view_suggestions = findViewById(R.id.horizontal_recycler_view_suggestions);
        Suggestions=new ArrayList<>();

        horizontalAdapter=new HorizontalAdapter(Suggestions);

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(Lyrics.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view_suggestions.setLayoutManager(horizontalLayoutManager);

        horizontal_recycler_view_suggestions.setAdapter(horizontalAdapter);

        // Check that you have the proper recording and saving permissions
        if (!checkPermissionFromDevice()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
        }
        if (checkPermissionFromDevice()) {
            //Initialize Audio Recorder
            recorder = new MediaRecorder();
        }

        //sPath = getApplicationContext().getFilesDir().getAbsolutePath();
        sPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MusicJump/";
        initVerses();
    }


    @Override
    protected void onPause() {
        super.onPause();
        EditText title, verse, songTitle;
        String titleText, verseText;
        ArrayList<String> verses = new ArrayList<String>();
        ArrayList<String> titles = new ArrayList<String>();
        songTitle = (EditText) findViewById(R.id.editText7);
        for (int i = 0; i < layout.getChildCount() - 1; i += 2) {
            title = (EditText) layout.getChildAt(i);
            verse = (EditText) layout.getChildAt(i + 1);
            titleText = title.getText().toString();
            verseText = verse.getText().toString();
            /* TODO: save strings 'titleText' and 'verseText' while keeping the ordering. */
            verses.add(verseText);
            titles.add(titleText);
        }
        current.setVerses(verses);
        current.setTitles(titles);
        String songname = songTitle.getText().toString();
        if (songname.equals("")) {
            current.setSongname("Default");
        }
        if (!songname.equals(current.getSongname())) {
            File oldFolder = new File(SerializationBase.pathGenerator(current));
            File newFolder = new File(sPath + songname + '/');
            boolean success = oldFolder.renameTo(newFolder);
            current.setSongname(songTitle.getText().toString());
        }
        try {
            SerializationBase.saveStop(songs, current.getSongname());
        } catch (Exception e){
            Log.v("Save_Stop Error : ",e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Initialize the activity by creating all saved verses and storing the ID's of the views for
     * each of these verses and their titles.
     */
    public void initVerses() {
        // Dessrialize test
        Log.d("Init:", "start");
        if (! new File(sPath + "/songs.ser").isFile()) {
            current = new Song("Default");
            songs.add(current);
            Log.d("Not_Found:", sPath + "/songs.ser");
        } else {
            Log.d("Read: ", "Start read");
            HashSet<User> users = null;
            String songsfile = "/songs.ser";
            String songfile = "/song.ser";
            songs = SerializationBase.genericLoad(songsfile, new HashSet<Song>());
            String songname = (String) SerializationBase.loadObject(songfile);
            Log.d("Read songname", songname);
            for (Song s : songs) {
                Log.d("Song ", s.getSongname());
                if (s.getSongname().equals(songname)) {
                    Log.d("Current_find", s.getSongname());
                    current = s;
                }
            }
            if (current  == null) {
                current = new Song("Default");
            }
        }
        //
        int verseCount = getVerseCountFromFile();
        EditText songTitle = findViewById(R.id.editText7);
        if (current.getSongname().equals("Default")) {
            songTitle.setHint(current.getSongname());
        } else {
            songTitle.setText(current.getSongname());
        }
        while (layout.getChildCount() > 1) {
            deleteVerse(layout);
        }
        for (int i = 0; i < verseCount; i++) {
            createVerse(getTextFromFile(i), getTitleFromFile(i), i * 2);
        }

        //Test Lyrics Suggestions
        //String suggestions = LyricsSuggestion.GetSuggestions(this,"tomato");
    }

    public void goToNotes(View view) {
        Intent intent = new Intent(Lyrics.this, Notes.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void getLyricSuggestion(View view) {
        EditText verse = (EditText)getCurrentFocus();
        Editable text = verse.getEditableText();
        String word = text.toString().substring(verse.getSelectionStart(), verse.getSelectionEnd());
        //String suggestion = LyricsSuggestion.GetSuggestions(this, word);
        LyricsSuggestion.GetSuggestions(this, word);
        //text.append(suggestion);
        verse.setText(text);
    }

    // suggestions are just rhymes for now
    public void onSuggestionReceived(ArrayList<String> rhymes){
        //Log.v("GetSuggestions", suggestion);
        String rhymesStr = "";
        for (String rhyme : rhymes) {
            rhymesStr += rhyme + ", ";
        }
        //EditText verse = (EditText)getCurrentFocus();
        //Editable text = verse.getEditableText();
        //text.append(rhymesStr);

        // Remove any previous suggestions and add new ones
        Suggestions.clear();
        Suggestions.addAll(rhymes);
        horizontalAdapter.notifyDataSetChanged();
    }

    /**
     * Create a verse view along along with its corresponding title view and store their ID's.
     *
     * @param text text of the verse
     * @param title title of the verse
     * @param index index at which the verse and its title are stored
     */
    public void createVerse(String text, String title, int index) {
        // index must be before last view in the layout.
        if (index >= layout.getChildCount()) {
            index = layout.getChildCount() - 1;
        }
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
        if (text.equals("Type verse here.")) {
            newVerse.setHint(text);
        } else if (text.equals("")) {
            newVerse.setHint("Type verse here.");
        } else {
            newVerse.setText(text);
        }
        newVerse.setInputType(VERSE_INPUT_TYPE);
        newVerse.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                updateVerseTitles();
            }
        });
        newVerse.addTextChangedListener(new TextWatcher() {
            private boolean doubleReturn = false;
            int split;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i > 0 && charSequence.charAt(i - 1) == '\n' && charSequence.charAt(i) == '\n') {
                    doubleReturn = true;
                    split = i -1;
                } else {
                    doubleReturn = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Executed on double return.
                //if (editable.charAt(editable.length() - 1) == '\n' && editable.charAt(editable.length() - 2) == '\n') {
                if (doubleReturn) {
                    String newVerseText = "Type verse here.";
                    // Get the index where we will place a new verse.
                    int i = layout.indexOfChild(getCurrentFocus()) + 1;
                    editable.delete(split, split + 2);
                    if (editable.length() >= split + 2) {
                        newVerseText = editable.subSequence(split, editable.length()).toString();
                        editable.delete(split, editable.length());
                    }
                    createVerse(newVerseText, (i / 2) + ".", i);
                    if (i < layout.getChildCount() - 1) {
                        layout.getChildAt(i + 1).requestFocus();
                    }
                }
            }
        });

        layout.addView(verseTitle, index);
        layout.addView(newVerse, index + 1);
    }

    /**
     * Create a new verse with default text and the next numbered title.
     *
     * @param view view from which this method is called
     */
    public void createNewVerse(View view) {
        createVerse("Type verse here.", ((layout.getChildCount() - 1) / 2) + ".", layout.getChildCount() - 1);
        updateVerseTitles();
    }

    /**
     * Delete the given verse from the layout and no longer keep track of it.
     *
     * @param view view from which this method is called
     * @param i index of verse to delete
     */
    public void deleteVerse(View view, int i) {
        View title = layout.getChildAt(i - (i % 2));
        View verse = layout.getChildAt(i - (i % 2) + 1);
        layout.removeView(title);
        layout.removeView(verse);
    }

    public void deleteVerse(View view) {
        deleteVerse(view, layout.getChildCount() - 2);
    }

    /**
     * Update the verse titles so that they are in a valid order.
     */
    public void updateVerseTitles() {
        EditText title;
        int nextVerseNum = 1;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lyricLayout);
        for (int i = 0; i < linearLayout.getChildCount() - 1; i += 2) {
            title = (EditText) linearLayout.getChildAt(i);
            // Check if this title is a number.
            String titleStr = title.getText().toString();
            if (titleStr.length() == 0 || titleStr.substring(0,1).matches("\\d+")) {
                title.setText(nextVerseNum + ".");
                nextVerseNum++;
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
        return "Type verse here.";
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /* ================ AUDIO RECORDING SECTION OF MAIN ACTIVITY ================ */

    /**
     * Called when the play/pause button is pressed
     * Updates the button image and runs associated function on a Thread
     * @param  view the button
     */
    public void playButtonPressed(View view) {
        ImageButton button = (ImageButton) view;
        ImageButton record = findViewById(R.id.recordButton);
        int icon, icon_2, icon_3;

        playing = !playing;
        if (playing) {
            record.setEnabled(false);
            icon = R.drawable.pause;
            icon_2 = R.drawable.record_grey;
            record.setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(), icon_2));
            player = new MediaPlayer();
            runOnThread(new Runnable() {
                public void run() {
                    startPlayback();
                }
            }, "Playing...");
        } else {
            icon_3 = R.drawable.record;
            record.setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(), icon_3));
            record.setEnabled(true);
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

    /**
     * Called when the recording button is pressed
     * Updates the button image and runs associated function on a Thread
     * @param view the button
     */
    public void recButtonPressed(View view) {
        ImageButton button = (ImageButton) view;
        ImageButton play = findViewById(R.id.playButton);
        int icon, icon_2, icon_3;

        recording = !recording;
        if (!recording) {
            play.setEnabled(true);
            icon = R.drawable.record;
            icon_2 = R.drawable.play;
            play.setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(), icon_2));
            createSaveDialogue();
        } else {
            icon_3 = R.drawable.play_grey;
            play.setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(), icon_3));
            play.setEnabled(false);
            icon = R.drawable.record_stop;
            int takeNumber = 1;
            currentVerse =  "Verse " + verseNumber + " Take " + takeNumber;
            setAudioPath(currentVerse);
            audioOutFile = new File(audioPath);
            while (audioOutFile.exists()) {
                takeNumber++;
                currentVerse =  "Verse " + verseNumber + " Take " + takeNumber;
                setAudioPath(currentVerse);
                audioOutFile = new File(audioPath);
            }

            try {
                audioOutStream = new FileOutputStream(audioOutFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setupMediaRecorder();
            runOnThread(new Runnable() {
                public void run() {
                    startRecording();
                }
            }, "Recording...");
        }
        button.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));
    }

    public void onDialogClickSaveRec(String saveString) {
        saveString = (saveString + ".3gp");
        boolean success = true;
        String saveStringPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/" + saveString;
        File newFile = new File(saveStringPath);

        if ((!saveString.equals(audioOutFile.getName())) && newFile.exists()) {
            DialogFragment dialog = new FileExistsDialogFragment();
            dialog.show(fragManager, "fileExists");
            success = false;
        } else if (!audioOutFile.renameTo(newFile)) {
//                System.out.println("RENAMING FAILED");
            success = false;
        }
        if (!success) {
            runOnThread(new Runnable() {
                public void run() {
                    dumpRecording();
                }
            }, "Save Canceled");

//            takeNumber--;
        } else {
            runOnThread(new Runnable() {
                public void run() {
                    stopRecording();
                }
            }, "Recording Saved");
        }
    }

    public void onDialogClickCancel() {
        runOnThread(new Runnable() {
            public void run() {
                dumpRecording();
            }
        }, "Save Canceled");
    }

    public String getDefaultText(){
        return  currentVerse;
    }

    /**
     * Sets the audio pathway for recording/playing
     */
    private void setAudioPath(String saveString) {
        //  sPath +
        audioPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/" + saveString + ".3gp";
    }

    /**
     * Sets up the MediaRecorder's input and output formats and sources
     */
    private void setupMediaRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.setOutputFile(audioOutStream.getFD());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs a function in a thread and prints a message via a Toast if the message is not blank
     * @param func function to be run on a new Thread
     * @param message print message
     */
    private void runOnThread(final Runnable func, String message) {
        new Thread(func).start();
        if (!message.equals(""))
            Toast.makeText(Lyrics.this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Starts the audio recording
     */
    private void startRecording() {
        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the audio recording
     */
    private void stopRecording() {
        recorder.stop();
        recorder.reset();
    }

    private void dumpRecording() {
        recorder.reset();
        if (!audioOutFile.delete()) {
            System.out.println("DELETION FAILED");
        }
    }

    /**
     * Starts audio playback
     */
    private void startPlayback(){
        try {
            player.setDataSource(audioPath);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    /**
     * Stops audio playback
     */
    private void stopPlayback(){
        if(player != null) {
            player.stop();
            player.release();
        }
    }

    private void createSaveDialogue() {
        DialogFragment dialog = new SaveRecDialogFragment();
        dialog.show(fragManager, "saving");
    }

    /**
     * Checks for all permissions needed for Audio
     * @param requestCode tag
     * @param permissions tag
     * @param grantResults tag
     */

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Mic Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Mic Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    /**
     * Checks if we currently have permissions
     * @return whether we have permissions
     */
    private boolean checkPermissionFromDevice() {
        boolean record_audio = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean store_file = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return record_audio && store_file;
    }

    /* ================ END OF AUDIO RECORDING SECTION OF MAIN ACTIVITY ================ */
}
