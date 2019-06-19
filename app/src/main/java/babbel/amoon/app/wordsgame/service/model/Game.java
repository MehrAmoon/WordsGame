package babbel.amoon.app.wordsgame.service.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by M.Amoon on 6/17/2019.
 */
public class Game {

    public static String Score = "" ;
    public static ArrayList<Integer> CorectWords;
    public static ArrayList<Integer> SelectedWords;
    public int Response = 0;

    //generate 10 random number from wordlists
    public static ArrayList<Integer> ShowingGamesWords(int size) {
        SelectedWords = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            SelectedWords.add(RandomNumberGenerator(0, size - 1));
        return SelectedWords;
    }

    //generate 5 correct answers from SelectedWords
    public static ArrayList<Integer> ShowingGamesCorrectWords() {
        CorectWords = new ArrayList<>();
        for (int j = 0; j < 5; j++)
            CorectWords.add(RandomNumberGenerator(0, SelectedWords.size()));
        return CorectWords;
    }

    //chek if answers come from user correct so add 10 to user score
    public static String CheckAnsewer(Boolean MustBeCorrect) {
        if (MustBeCorrect)
            Score = "correct";
        else Score = "wrong";

        return Score;
    }

    //generate random number
    public static int RandomNumberGenerator(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }


}
