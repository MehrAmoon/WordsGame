package babbel.amoon.app.wordsgame.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M.Amoon on 6/17/2019.
 */
public class Words {
    //classes to retrieve data from Retrofit json
    @SerializedName("text_eng")
    @Expose
    private String textEng;
    @SerializedName("text_spa")
    @Expose
    private String textSpa;

    public String getTextEng() {
        return textEng;
    }

    public void setTextEng(String textEng) {
        this.textEng = textEng;
    }

    public String getTextSpa() {
        return textSpa;
    }

    public void setTextSpa(String textSpa) {
        this.textSpa = textSpa;
    }
}
