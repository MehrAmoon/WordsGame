package babbel.amoon.app.wordsgame;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import babbel.amoon.app.wordsgame.service.model.APIService;
import babbel.amoon.app.wordsgame.service.model.Game;
import babbel.amoon.app.wordsgame.service.model.RetroClass;
import babbel.amoon.app.wordsgame.viewmodel.WordsViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("babbel.amoon.app.wordsgame", appContext.getPackageName());
    }

    @Test
    public void checkAnswerTrue() {
        assertEquals("correct" , Game.CheckAnsewer(true));
    }

    @Test
    public void checkAnswerFalse(){
        assertEquals("wrong" , Game.CheckAnsewer(false));
    }


    @Test
    public void testApiResponse() {
        APIService apiService = RetroClass.getAPIService();
        apiService.GetWordsFromURL().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("Success");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failure");
            }
        });

    }




}
