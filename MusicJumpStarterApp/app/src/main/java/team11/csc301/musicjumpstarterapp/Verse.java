package team11.csc301.musicjumpstarterapp;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Verse extends RelativeLayout {

    // Finals for verse attributes, layout.
    public static final int TITLE_INPUT_TYPE = InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
    public static final int BODY_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int CHORDS_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
    public static final int SIDE_MARGIN = 120;
    public static final int TITLE_TOP_MARGIN = 0;
    public static final int BODY_TOP_MARGIN = 120;
    public static final int CHORDS_TOP_MARGIN = 70;
    public static final int LINE_SPACING_EXTRA = 70;

    // Finals for editing mode.
    public static final int BODY = 0;
    public static final int CHORDS = 1;


    private RelativeLayout.LayoutParams title_margins;
    private RelativeLayout.LayoutParams body_margins;
    private RelativeLayout.LayoutParams chords_margins;
    private EditText title;
    private EditText body;
    private EditText chords;

    public Verse(Lyrics context, String bodyText, String titleText) {
        super(context);

        title_margins = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        body_margins = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        chords_margins = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        title_margins.setMargins(SIDE_MARGIN, TITLE_TOP_MARGIN, SIDE_MARGIN, 0);
        body_margins.setMargins(SIDE_MARGIN, BODY_TOP_MARGIN, SIDE_MARGIN, 0);
        chords_margins.setMargins(SIDE_MARGIN, CHORDS_TOP_MARGIN, SIDE_MARGIN, 0);

        initTitle(titleText);
        initBody(bodyText);
        initChords("");

        setEditingMode(BODY);
    }

    private void initTitle(String titleText) {
        title = new EditText(getContext());
        title.setLayoutParams(title_margins);
        title.setInputType(TITLE_INPUT_TYPE);
        title.setText(titleText);
        title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ((Lyrics) getContext()).updateVerseTitles();
            }
        });
        title.setBackgroundColor(Color.TRANSPARENT);

        addView(title);
    }

    private void initBody(String bodyText) {
        body = new EditText(getContext());
        body.setLayoutParams(body_margins);
        if (bodyText.equals("Type verse here.")) {
            body.setHint("Type verse here.");
        } else if (bodyText.equals("")) {
            body.setHint("Type verse here.");
        } else {
            body.setText(bodyText);
        }
        body.setInputType(BODY_INPUT_TYPE);
        body.setLineSpacing(LINE_SPACING_EXTRA, 0);
        body.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ((Lyrics) getContext()).updateVerseTitles();
            }
        });
        body.addTextChangedListener(new TextWatcher() {
            private boolean doubleReturn = false; // True if the user typed in a newline twice at the same position.
            private boolean insertNewline = false; // True if we just added a newline and need to add another to keep it double-spaced.
            private boolean deleteNewline = false; // True if we just deleted a newline and need to delete another to keep it double-spaced.
            private int split;
            private int count = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i1 == 1 && i < charSequence.length() && charSequence.charAt(i) == '\n') {
                    deleteNewline = !deleteNewline;
                    split = i;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 == 1 && i > 1 && i < charSequence.length() && charSequence.charAt(i - 1) == '\n' && charSequence.charAt(i) == '\n' && charSequence.charAt(i - 2) == '\n') {
                    doubleReturn = true;
                    split = i - 2;
                } else {
                    doubleReturn = false;
                    if (i2 == 1 && i < charSequence.length() && charSequence.charAt(i) == '\n') {
                        insertNewline = !insertNewline;
                        split = i;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (doubleReturn) {
                    String newVerseText = "";
                    int i = getIndex();
                    // Delete extra newlines at end of this verse.
                    editable.delete(split, split + 3);
                    if (editable.length() >= split) {
                        // If there was more text after this split, delete it from this verse and
                        // set it as the text for the new verse.
                        newVerseText = editable.subSequence(split, editable.length()).toString();
                        editable.delete(split, editable.length());
                    }
                    Verse newVerse = new Verse((Lyrics) getContext(), newVerseText, "");
                    ((Lyrics) getContext()).layout.addView(newVerse, getIndex() + 1);
                    newVerse.requestBodyFocus();
                }
                if (insertNewline) {
                    editable.insert(split, "\n");
                }
                if (deleteNewline && split > 0) {
                    editable.delete(split - 1, split);
                }
            }
        });
        body.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = body.getSelectionStart();
                Editable text = body.getText();
                if (text.length() > i && i > 0 && text.charAt(i - 1) == '\n' && text.charAt(i) == '\n') {
                    body.setSelection(i + 1);
                }
            }
        });

        addView(body);
    }

    private void initChords(String chordsText) {
        chords = new EditText(getContext());
        chords.setLayoutParams(chords_margins);
        chords.setInputType(CHORDS_INPUT_TYPE);
        chords.setLineSpacing(LINE_SPACING_EXTRA, 0);
        chords.setText(chordsText);
        chords.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ((Lyrics) getContext()).updateVerseTitles();
            }
        });
        chords.setBackgroundColor(Color.TRANSPARENT);
        chords.addTextChangedListener(new TextWatcher() {
            private boolean doubleReturn = false; // True if the user typed in a newline twice at the same position.
            private boolean insertNewline = false; // True if we just added a newline and need to add another to keep it double-spaced.
            private boolean deleteNewline = false; // True if we just deleted a newline and need to delete another to keep it double-spaced.
            private int split;
            private int count = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i1 == 1 && i < charSequence.length() && charSequence.charAt(i) == '\n') {
                    deleteNewline = !deleteNewline;
                    split = i;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 == 1 && i < charSequence.length() && charSequence.charAt(i) == '\n') {
                    insertNewline = !insertNewline;
                    split = i;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (insertNewline) {
                    editable.insert(split, "\n");
                }
                if (deleteNewline && split > 0) {
                    editable.delete(split - 1, split);
                }
            }
        });
        chords.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = chords.getSelectionStart();
                Editable text = chords.getText();
                if (text.length() > i && i > 0 && text.charAt(i - 1) == '\n' && text.charAt(i) == '\n') {
                    chords.setSelection(i + 1);
                }
            }
        });

        addView(chords);
    }

    /**
     * Set the editing mode for this verse to allow editing of only the body or only the chords.
     *
     * @param mode mode to set to
     */
    public void setEditingMode(int mode) {
        switch(mode) {
            case BODY:
                chords.setEnabled(false);
                body.setEnabled(true);
                body.bringToFront();
                break;
            case CHORDS:
                body.setEnabled(false);
                chords.setEnabled(true);
                chords.bringToFront();
                break;
        }
    }

    /**
     * Bring the body of this verse into focus.
     *
     * @return result of view.requestFocus()
     */
    public boolean requestBodyFocus() {
        return body.requestFocus();
    }

    /**
     * Get the selection from the body of this verse.
     *
     * @return selection as a string or null if no selection
     */
    public String getSelection() {
        if (body.getSelectionStart() != -1 && body.getSelectionEnd() != -1) {
            return body.getText().toString().substring(body.getSelectionStart(), body.getSelectionEnd());
        } else {
            return null;
        }
    }

    /**
     * Get the index in the parent layout of this verse.
     *
     * @return the index of this verse
     */
    public int getIndex() {
        return ((Lyrics) getContext()).layout.indexOfChild(this);
    }

    public String getTitle() {
        return title.getText().toString();
    }

    public String getBody() {
        return body.getText().toString();
    }

    public String getChords() {
        return chords.getText().toString();
    }

    public void setTitle(String titleText) {
        title.setText(titleText);
    }

    public void setBody(String bodyText) {
        body.setText(bodyText);
    }

    public void setChords(String chordsText) {
        chords.setText(chordsText);
    }
}
