package babbel.amoon.app.wordsgame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import java.util.List;

import babbel.amoon.app.wordsgame.service.model.APIService;
import babbel.amoon.app.wordsgame.service.model.Game;
import babbel.amoon.app.wordsgame.service.model.RetroClass;
import babbel.amoon.app.wordsgame.service.model.Words;
import babbel.amoon.app.wordsgame.service.repository.WordsRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by M.Amoon on 6/17/2019.
 */
public class WordsViewModel extends AndroidViewModel {
    private Game game;
    public MutableLiveData<String> Word = new MutableLiveData<>();
    public MutableLiveData<String> WordMeaning = new MutableLiveData<>();
    public MutableLiveData<String> Response = new MutableLiveData<>();
    private WordsRepository wordsRepository;


    public ObservableField<String> wordVal = new ObservableField<>("");

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Words>> WordsList;

    public WordsViewModel(@NonNull Application application) {
        super(application);
    }

    //we will call this method to get the data
    public LiveData<List<Words>> getWords() {
        //if the list is null
        if (WordsList == null) {
            WordsList = new MutableLiveData<List<Words>>();
            //we will load it asynchronously from server in this method
            loadWords();
        }

        //finally we will return the list
        return WordsList;
    }




    //This method is using Retrofit to get the JSON data from URL
    private void loadWords() {

        APIService apiService = RetroClass.getAPIService();
        apiService.GetWordsFromURL().enqueue(new Callback<List<Words>>() {

            @Override
            public void onResponse(Call<List<Words>> call, retrofit2.Response<List<Words>> response) {
                //finally we are setting the list to our MutableLiveData
                WordsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Words>> call, Throwable t) {

            }


        });


    }






}


