package team11.csc301.musicjumpstarterapp;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class Verse extends RelativeLayout {

    public static final int BODY_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int TITLE_INPUT_TYPE = InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
    public static final int CHORDS_INPUT_TYPE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
    public static final int LEFT_MARGINS = 120;
    public static final int RIGHT_MARGINS = 120;
    public static final int TITLE_TOP_MARGINS = 0;
    public static final int BODY_TOP_MARGINS = 120;
    public static final int CHORDS_TOP_MARGINS = 70;


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
        title_margins.setMargins(LEFT_MARGINS, TITLE_TOP_MARGINS, RIGHT_MARGINS, 0);
        body_margins.setMargins(LEFT_MARGINS, BODY_TOP_MARGINS, RIGHT_MARGINS, 0);
        chords_margins.setMargins(LEFT_MARGINS, CHORDS_TOP_MARGINS, RIGHT_MARGINS, 0);

        initTitle(titleText);
        initBody(bodyText);
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
        body.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ((Lyrics) getContext()).updateVerseTitles();
            }
        });
        body.addTextChangedListener(new TextWatcher() {
            private boolean doubleReturn = false;
            int split;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i > 0 && charSequence.charAt(i - 1) == '\n' && charSequence.charAt(i) == '\n') {
                    doubleReturn = true;
                    split = i - 1;
                } else {
                    doubleReturn = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Executed on double return.
                //if (editable.charAt(editable.length() - 1) == '\n' && editable.charAt(editable.length() - 2) == '\n') {
                if (doubleReturn) {
                    String newVerseText = "";
                    // Get the index where we will place a new verse.
                    int i = getIndex();
                    editable.delete(split, split + 2);
                    if (editable.length() >= split) {
                        newVerseText = editable.subSequence(split, editable.length()).toString();
                        editable.delete(split, editable.length());
                    }
                    Verse newVerse = new Verse((Lyrics) getContext(), newVerseText, "");
                    ((Lyrics) getContext()).layout.addView(newVerse, getIndex() + 1);
                    newVerse.requestTitleFocus();
                }
            }
        });

        addView(body);
    }



    public int getIndex() {
        return ((Lyrics) getContext()).layout.indexOfChild(this);
    }

    public String getTitle() {
        return title.getText().toString();
    }

    public String getBody() {
        return body.getText().toString();
    }

    public void setTitle(String titleText) {
        title.setText(titleText);
    }

    public void setBody(String bodyText) {
        body.setText(bodyText);
    }

    public boolean requestTitleFocus() {
        return body.requestFocus();
    }

    public String getSelection() {
        if (body.getSelectionStart() != -1) {
            return body.getText().toString().substring(body.getSelectionStart(), body.getSelectionEnd());
        } else {
            return null;
        }
    }
}
