package babbel.amoon.app.wordsgame.service.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by M.Amoon on 6/17/2019.
 */
public interface APIService {
    //write get to retrive data from service
    @GET("words.json")
    Call<List<Words>> GetWordsFromURL();

}
