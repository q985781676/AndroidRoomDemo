package com.example.roomtest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//最好写成singleton
@Database(entities = {Word.class},version = 3,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase INSTANCE;

    static synchronized WordDatabase getWordDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
//                    .addMigrations(MIGRATION_2_3)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    //多个Entity多个Dao
    public abstract WordDao getWordDao();

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN data INTEGER NOT NULL DEFAULT 1");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL," +
                    "English_Word TEXT,Chinese_Meaning TEXT )");
            database.execSQL("INSERT INTO word_temp(id,English_Word,Chinese_Meaning)" +
                    "SELECT id,English_Word,Chinese_Meaning FROM word");
            database.execSQL("DROP TABLE word");
            database.execSQL("ALTER TABLE word_temp RENAME TO word");
        }
    };

}
