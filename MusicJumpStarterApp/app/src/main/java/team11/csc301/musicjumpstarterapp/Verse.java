package team11.csc301.musicjumpstarterapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Iterator;
import java.util.concurrent.Callable;

/**
 * This is a verse for a particular song.
 *
 * This verse includes a body, a title, and chords, each of which can be edited and retrieved. This
 * verse is displayed as a single layout and can be treated as a single entity in its parent
 * context, which must be an instance of Lyrics.
 */
public class Verse extends RelativeLayout {

    // Finals for verse attributes and the layout.
    public static final int TITLE_INPUT_TYPE = InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
    public static final int BODY_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int CHORDS_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
    public static final int SIDE_MARGIN = 120;
    public static final int TITLE_TOP_MARGIN = 0;
    public static final int BODY_TOP_MARGIN = 120;
    public static final int CHORDS_TOP_MARGIN = 63;
    public static final int LINE_SPACING_EXTRA = 70;
    // Finals for editing mode.
    public static final int BODY = 0;
    public static final int CHORDS = 1;

    // Margins for this verse.
    private RelativeLayout.LayoutParams title_margins;
    private RelativeLayout.LayoutParams body_margins;
    private RelativeLayout.LayoutParams chords_margins;
    // The actual textviews for this verse.
    private EditText title;
    private EditText body;
    private EditText chords;

    /**
     * Used by RelativeLayout.
     */
    public Verse(Context context) {
        super(context);
    }

    /**
     * Create a verse from the given title, lyrics, and chords.
     *
     * @param context parent context, must be an instance of Lyrics
     * @param titleText title of this verse, will default to the number required by its parent
     *                  context if its first character is a number or if empty
     * @param bodyText lyrics of this verse, will be set as a hint if 'Type verse here' and will
     *                 default to that if given the empty string
     * @param chordsText chords of this verse
     */
    public Verse(Lyrics context, String titleText, String bodyText, String chordsText) {
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
        initChords(chordsText);

        checkChords();
        setEditingMode(BODY);
    }

    /**
     * Initialize the title of this verse.
     *
     * @param titleText title to be displayed
     */
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

    /**
     * Initialize the body or lyrics of this verse.
     *
     * @param bodyText lyrics to be displayed
     */
    private void initBody(String bodyText) {
        body = new EditText(getContext());
        body.setLayoutParams(body_margins);
        switch(bodyText) {
            case "Type verse here.":
                body.setHint("Type verse here.");
                break;
            case "":
                body.setHint("Type verse here.");
                break;
            default:
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

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i1 == 1 && i < charSequence.length() && charSequence.charAt(i) == '\n') {
                    deleteNewline = !deleteNewline;
                    split = i;
                }
                if (i1 == 1) {
                    updateChords(charSequence.subSequence(i, i + 1).toString(), "", i);
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
                if (i2 == 1) {
                    updateChords("", charSequence.subSequence(i, i + 1).toString(), i);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (doubleReturn) {
                    String newVerseText = "";
                    // Delete extra newlines at end of this verse.
                    editable.delete(split, split + 3);
                    if (editable.length() >= split) {
                        // If there was more text after this split, delete it from this verse and
                        // set it as the text for the new verse.
                        newVerseText = editable.subSequence(split, editable.length()).toString();
                        editable.delete(split, editable.length());
                    }
                    Verse newVerse = new Verse((Lyrics) getContext(), "", newVerseText, "");
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

    /**
     * Initialize the chords of this verse.
     *
     * @param chordsText chords to be displayed
     */
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
            private boolean insertNewline = false; // True if we just added a newline and need to add another to keep it double-spaced.
            private boolean deleteNewline = false; // True if we just deleted a newline and need to delete another to keep it double-spaced.
            private int split;

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
     * Performs a check operation on the chords of this verse by ensuring that the length of each
     * line of the chords is as close as possible to the length of the corresponding line in the
     * body of this verse. Will delete non-space characters.
     */
    public void checkChords() {
        Paint bodyPaint = new Paint(body.getPaint());
        Paint chordsPaint = new Paint(body.getPaint());
        // Reference to body of the lyrics.
        String bodyString = getBody();
        // Will be our new chords once we're done here.
        StringBuilder chordsString = new StringBuilder(getChords());
        // The indices of the start and end of the lines as we loop through them.
        int bodyLineEnd, chordsLineEnd = 0;
        int bodyLineStart = 0, chordsLineStart = 0;

        // Loop through lines, updating each separately.
        while (bodyLineStart < bodyString.length()) {
            // Set the indices pointing to the end of the current lines.
            bodyLineEnd = bodyString.substring(bodyLineStart).indexOf('\n') + bodyLineStart;
            if (bodyLineEnd == bodyLineStart - 1) {
                bodyLineEnd = bodyString.length();
            }
            chordsLineEnd = chordsString.substring(chordsLineStart).indexOf('\n') + chordsLineStart;
            if (chordsLineEnd == chordsLineStart - 1) {
                chordsLineEnd = chordsString.length();
            }

            // Perform the actual update of this line of the chords.
            float bodyWidth = bodyPaint.measureText(bodyString.substring(bodyLineStart, bodyLineEnd));
            float chordsWidth = chordsPaint.measureText(chordsString.substring(chordsLineStart, chordsLineEnd));
            while (chordsWidth > bodyWidth) {
                chordsString.delete(chordsLineEnd - 1, chordsLineEnd);
                chordsLineEnd--;
                chordsWidth = chordsPaint.measureText(chordsString.substring(chordsLineStart, chordsLineEnd));
            }
            while (chordsWidth < bodyWidth) {
                chordsString.insert(chordsLineEnd, " ");
                chordsLineEnd++;
                chordsWidth = chordsPaint.measureText(chordsString.substring(chordsLineStart, chordsLineEnd));
            }

            // Set the indices pointing to the start of the current lines.
            chordsLineStart = chordsLineEnd + 2;
            if (chordsLineStart == chordsString.length() + 2) {
                chordsString.append("\n\n "); // chordsLineStart points to the space.
            } else if (chordsLineStart == chordsString.length()) {
                chordsString.append(" "); // chordsLineStart points to the space.
            } else if (chordsLineStart > chordsString.length()) {
                throw new StringIndexOutOfBoundsException();
            }
            bodyLineStart = bodyLineEnd + 2;
        }
        if (chordsLineEnd != chordsString.length()) {
            chordsString.delete(chordsLineEnd, chordsString.length());
        }

        setChords(chordsString.toString());
    }

    /**
     * Updates the chords to account for a change in the body of the verse. Will add spaces to
     * account for an insertion at index i of the body (indicated by prev being the empty string and
     * now being a string of the inserted character), or will delete spaces to account for a
     * deletion at index i of the body (indicated by an empty now and a character in prev). Will not
     * delete non-space characters. Will attempt to make the change as close to the place in the
     * body of the verse as possible.
     *
     * @param prev one character string representing the character removed (or null if insertion)
     * @param now one character string representing the character inserted (or null if deletion)
     * @param i index into the body of the verse where the change was made
     */
    public void updateChords(String prev, String now, int i) {
        Paint bodyPaint = new Paint(body.getPaint());
        Paint chordsPaint = new Paint(body.getPaint());
        LineIterator bodyLines = getBodyIterator();
        LineIterator chordsLines = getChordsIterator();
        String bodyLine = "", chordsLine = "";

        // Find the line where the change occurred.
        while (bodyLines.hasNext()) {
            bodyLine = bodyLines.next();
            if (!chordsLines.hasNext()) {
                if (getChords().length() == 0 || getChords().charAt(getChords().length() - 1) == '\n') {
                    // Then the last line was simply empty.
                    setChords(getChords() + " ");
                } else {
                    setChords(getChords() + "\n\n ");
                }
            }
            chordsLine = chordsLines.next();
            if (i <= bodyLines.end) {
                i -= bodyLines.start;
                break;
            }
        }
        if (i > bodyLine.length()) {
            throw new IndexOutOfBoundsException();
        }

        // Perform the actual update.
        float bodyWidth = bodyPaint.measureText(bodyLine.substring(0, i));
        int j = getIndexOfWidth(bodyWidth, chordsLine, chordsPaint);
        StringBuilder newChords = new StringBuilder(getChords());
        if (prev.matches("")) {
            // Then this was an insert.
            int extraSpace = Math.round(bodyPaint.measureText(now) / chordsPaint.measureText(" "));
            for (int k = 0; k < extraSpace; k++) {
                newChords.insert(j + chordsLines.start, " ");
            }
        } else {
            // Then this was a delete.
            int extraSpace = Math.round(bodyPaint.measureText(prev) / chordsPaint.measureText(" "));
            for (int k = 0; k < extraSpace; k++) {
                while (j + chordsLines.start < newChords.length() && newChords.charAt(j + chordsLines.start) != ' ') {
                    j++;
                }
                newChords.delete(j + chordsLines.start, j + chordsLines.start + 1);
            }
        }

        setChords(newChords.toString());
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
                checkChords();
                break;
            case CHORDS:
                body.setEnabled(false);
                chords.setEnabled(true);
                chords.bringToFront();
                checkChords();
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
     * Get an iterator over the lines of the body of this verse.
     *
     * @return a line iterator for this verse's lyrics/body
     */
    public LineIterator getBodyIterator() {
        return new LineIterator(new Callable<String>() {
            @Override
            public String call() {
                return getBody();
            }
        });
    }

    /**
     * Get an iterator over the lines of the chords of this verse.
     *
     * @return a line iterator for this verse's chords
     */
    public LineIterator getChordsIterator() {
        return new LineIterator(new Callable<String>() {
            @Override
            public String call() {
                return getChords();
            }
        });
    }

    /**
     * Get the index in the parent layout of this verse.
     *
     * @return the index of this verse
     */
    public int getIndex() {
        return ((Lyrics) getContext()).layout.indexOfChild(this);
    }

    /**
     * Get the title of this verse.
     *
     * @return title as a string
     */
    public String getTitle() {
        return title.getText().toString();
    }

    /**
     * Get the body/lyrics of this verse.
     *
     * @return body as a string
     */
    public String getBody() {
        return body.getText().toString();
    }

    /**
     * Get the chords of this verse.
     *
     * @return chords as a string
     */
    public String getChords() {
        return chords.getText().toString();
    }

     /**
     * Set the title of this verse.
     *
     * @param titleText the new title of this verse
     */
    public void setTitle(String titleText) {
        title.setText(titleText);
    }

    /**
     * Set the body/lyrics of this verse.
     *
     * @param bodyText the new lyrics of this verse
     */
    public void setBody(String bodyText) {
        body.setText(bodyText);
    }

    /**
     * Set the chords of this verse.
     *
     * @param chordsText new chords of this verse
     */
    public void setChords(String chordsText) {
        chords.setText(chordsText);
    }

    /**
     * An iterator which iterates over the lines of the string returned by the function given as a
     * parameter to the iterator. The string may add lines while its iterator is being used but it
     * is undefined what will happen if lines are deleted or changed.
     *
     * The string must be in the format of the chords or lyrics in this class.
     */
    class LineIterator implements Iterator<String> {
        // The start and end index of the current line.
        private int start = 0;
        private int end = -2;
        Callable<String> stringGetter;

        LineIterator(Callable<String> stringGetter) {
            super();
            this.stringGetter = stringGetter;
        }

        @Override
        public boolean hasNext() {
            try {
                return end + 2 < stringGetter.call().length(); // + 2 ensures not an empty line
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        public String next() {
            try {
                start = end + 2;
                end = stringGetter.call().substring(start).indexOf('\n') + start;
                if (end == start - 1) {
                    end = stringGetter.call().length();
                }
                return stringGetter.call().substring(start, end);
            } catch (Exception e) {
                return null;
            }
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    /**
     * Get an index within 1 of being the given width from the beginning of the given string.
     *
     * Uses binary search.
     *
     * @param width desired width of substring from 0 to the returned index in the given string
     * @param s string from which to get the index from
     * @param paint used to measure the width
     * @return an index into s which gives a prefix of approximately width length
     */
    private int getIndexOfWidth(float width, String s, Paint paint) {
        int a = 0, b = s.length() - 1;
        int i = b / 2;
        while (true) {
            if (i == 0) {
                break;
            } else if (paint.measureText(s.substring(0, i)) > width) {
                b = i;
                i -= (i - a) / 2;
                if (i == b) {
                    break;
                }
            } else {
                a = i;
                i += (b - i) / 2;
                if (i == a) {
                    break;
                }
            }
        }
        return i;
    }
}
