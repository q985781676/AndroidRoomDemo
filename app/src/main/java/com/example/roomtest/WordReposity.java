package com.example.roomtest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordReposity {

    private WordDao wordDao;
    private LiveData<List<Word>> allWordLive;

    public WordReposity(Context context) {
        WordDatabase wordDatabase = WordDatabase.getWordDatabase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWordLive = wordDao.queryAllWords();
    }

    public LiveData<List<Word>> getAllWordLive() {
        return allWordLive;
    }

    public void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }

    public void updateWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    public void deleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    public void deleteAllWords(){
        new DeleteAllAsyncTask(wordDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {

            wordDao.insertWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {

            wordDao.updateWords(words);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteAllWords();
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

}
