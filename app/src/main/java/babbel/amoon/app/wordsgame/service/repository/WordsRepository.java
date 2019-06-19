package babbel.amoon.app.wordsgame.service.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

/**
 * Created by M.Amoon on 6/17/2019.
 */
public class WordsRepository {
    private static Context context;

    public WordsRepository() {

    }

    //created to recive words from local gson but now not uses anymore
    public MutableLiveData<String> getResponse() {
        final MutableLiveData<String> data = new MutableLiveData<>();
        return data;
    }




//        parse data with json
//        public static void loadJsonFile() {
//
//        InputStream inputStream = context.getResources().openRawResource(R.raw.words_v2);
//        String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();
//        try {
//            Gson gson = new Gson();
//            Words words = gson.fromJson(jsonString, Words.class);
//            for (int i = 0; i < words.getTextEng().length(); i++) {
//                WordsList.add(words.getTextEng());
//                WordsList.add(words.getTextSpa());
//
//                WordsWithMeaningsList.add(WordsList);
//                words.setTextEng(words.getTextEng());
//                words.setTextSpa(words.getTextSpa());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
