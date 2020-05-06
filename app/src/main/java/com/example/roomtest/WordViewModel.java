package com.example.roomtest;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;


/**
 * @author 98578
 */
public class WordViewModel extends AndroidViewModel {

    private WordReposity wordReposity;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordReposity = new WordReposity(application);
    }

    public LiveData<List<Word>> getAllWordLive() {
        return wordReposity.getAllWordLive();
    }

    public void insertWords(Word... words){
        wordReposity.insertWords(words);
    }

    public void updateWords(Word... words){
        wordReposity.updateWords(words);
    }

    public void deleteWords(Word... words){
        wordReposity.deleteWords(words);
    }

    public void deleteAllWords(){
        wordReposity.deleteAllWords();
    }

}
