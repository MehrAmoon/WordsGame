package babbel.amoon.app.wordsgame.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import babbel.amoon.app.wordsgame.service.model.Game;
import babbel.amoon.app.wordsgame.service.model.Words;
import babbel.amoon.app.wordsgame.service.repository.WordsRepository;
import babbel.amoon.app.wordsgame.viewmodel.WordsViewModel;
import babbel.amoon.app.wordsgame.R;
import babbel.amoon.app.wordsgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private WordsViewModel wordsViewModel;
    private WordsRepository wordsRepository;
    private Game game;
    private int i = 0;
    public static Boolean MustBeCorrect = false;
    int CorrectScore = 1;
    int WrongScore = 1;
    String PermanentScore = "";
    Boolean FirstTime = true;
    int DelayTime = 5000;
    Handler handler = null;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // Inflate view and obtain an instance of the binding class.
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //provides wordsviewmodel
        wordsViewModel = ViewModelProviders.of(this).get(WordsViewModel.class);
        // Specify the current activity as the lifecycle owner.
        activityMainBinding.setLifecycleOwner(this);


        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage(getString(R.string.FetchWords));
        progress.setCancelable(false);
        progress.show();


        //getting the words and meaning from retrofit and show it in main_activity
        wordsViewModel = ViewModelProviders.of(this).get(WordsViewModel.class);
        wordsViewModel.getWords().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged(@Nullable final List<Words> wordList) {
                final ArrayList<Integer> ShowingWords = Game.ShowingGamesWords(wordList.size());
                final ArrayList<Integer> ShowingCorrectWords = Game.ShowingGamesCorrectWords();

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        if (FirstTime) {
                            progress.dismiss();
                            FirstTime = false;
                        }
                        PermanentScore="";
                        activityMainBinding.Word.setText(wordList.get(ShowingWords.get(i)).getTextEng());
                        if (ShowingCorrectWords.contains(i)) {
                            MustBeCorrect = true;
                            activityMainBinding.WordMeaning.setText(wordList.get(ShowingWords.get(i)).getTextSpa());
                            MeaningAnimation();
                        } else {
                            MustBeCorrect = false;
                            activityMainBinding.WordMeaning.setText(wordList.get(Game.RandomNumberGenerator(0, wordList.size())).getTextSpa());
                            MeaningAnimation();
                        }
                        i++;
                        if (i < 10) {
                            handler.postDelayed(this, 5000);
                        }


                    }
                }, DelayTime);


            }
        });

        //chek if right buttun clicked by user is correct or not
        activityMainBinding.rightAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermanentScore = game.CheckAnsewer(MustBeCorrect);

            }
        });

        //chek if wrong buttun clicked by user is correct or not
        activityMainBinding.WrongAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermanentScore = game.CheckAnsewer(!MustBeCorrect);

            }
        });


    }

    //show the dialog of ending game
    public void GameEnded() {

        GameEndDialog dialog = GameEndDialog.newInstance(this);
        dialog.setCancelable(false);
        Bundle bundle = new Bundle();
        bundle.putString("CorrectScore", String.valueOf(CorrectScore-1));
        bundle.putString("WrongScore", String.valueOf(WrongScore-1));
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), String.valueOf(R.string.game_end_dialog_headline));



    }

    //everything about wordmeaning animation
    public void MeaningAnimation() {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int min = 50;
        int max = displaymetrics.widthPixels - (displaymetrics.widthPixels / 3);
        Random r = new Random();
        float dx = r.nextInt(max - min + 1) + min;//  * displaymetrics.widthPixels;
        activityMainBinding.WordMeaning.setTranslationX(dx);

        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("translationY", 0f, 10000f);
        PropertyValuesHolder opacity = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.1f);


        ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(activityMainBinding.WordMeaning, pvhY);
        animation.setDuration(10000);
        animation.start();


        ObjectAnimator animation2 = ObjectAnimator.ofPropertyValuesHolder(activityMainBinding.WordMeaning, opacity);
        animation2.setDuration(3000);
        animation2.start();


        animation2.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                if (PermanentScore.contentEquals("correct")) {
                    activityMainBinding.UserScoreRight.setText("Corrects : " + CorrectScore++);
                } else {
                    activityMainBinding.UserScoreWrong.setText("Wrongs : " + WrongScore++);
                }


                if (i == 10) {
                    GameEnded();
                }
            }
        });


    }

}
