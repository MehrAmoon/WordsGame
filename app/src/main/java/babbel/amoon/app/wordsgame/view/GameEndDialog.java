package babbel.amoon.app.wordsgame.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import babbel.amoon.app.wordsgame.R;


/**
 * Created by M.Amoon on 6/18/2019.
 */
public class GameEndDialog extends DialogFragment {


    private View rootView;
    private MainActivity activity;
    private static String UserScore;
    TextView UserScoreextView;

    public static GameEndDialog newInstance(MainActivity activity) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //initialize layout parameters
        initializeView();
        //create dialog for ending game
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initializeView() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);
        UserScoreextView = (TextView) rootView.findViewById(R.id.user_score);
        //recive data when game ends
        Bundle bundle = getArguments();
        String CorrectScore = bundle.getString("CorrectScore", "");
        String WrongScore = bundle.getString("WrongScore", "");
        UserScore = "You had " + CorrectScore + " Correct and " + WrongScore + " Wrong";

        UserScoreextView.setText(UserScore);
    }


}
