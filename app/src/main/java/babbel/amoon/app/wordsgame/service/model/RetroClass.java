package babbel.amoon.app.wordsgame.service.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by M.Amoon on 6/17/2019.
 */
public class RetroClass {
    //declare baseurl for retrieving data
    private static final String BaseURL = "https://gist.githubusercontent.com/DroidCoder/7ac6cdb4bf5e032f4c737aaafe659b33/raw/baa9fe0d586082d85db71f346e2b039c580c5804/";

    //create builder based on baseurl
    private static Retrofit getRetroinstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create(gson)).build();

    }


    //declare apiservice we will need in WordsViewModel class
    public static APIService getAPIService() {
        return getRetroinstance().create(APIService.class);

    }

}
