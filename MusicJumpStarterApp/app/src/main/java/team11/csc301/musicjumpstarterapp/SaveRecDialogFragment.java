package team11.csc301.musicjumpstarterapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Marco Ly on 2018-03-09.
 */

public class SaveRecDialogFragment extends DialogFragment {
    public interface SaveRecDialogListener {
        String getDefaultText();
        void onDialogClickSaveRec(String saveText);
        void onDialogClickCancel();
    }

    private EditText recordingName;
    private String defaultText;
    // Use this instance of the interface to deliver action events
    private SaveRecDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (SaveRecDialogListener) activity;
            defaultText = listener.getDefaultText();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_save_audio, null);

        recordingName = dialogView.findViewById(R.id.recordingName);
        recordingName.setText(defaultText);

        builder.setMessage(R.string.dialog_save_rec)
                .setView(dialogView)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogClickSaveRec(recordingName.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogClickCancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
