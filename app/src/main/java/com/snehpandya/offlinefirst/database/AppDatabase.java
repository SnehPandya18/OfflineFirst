package com.snehpandya.offlinefirst.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by sneh.pandya on 10/11/17.
 */

    //TODO: 05. Setup Room Database

@Database(entities = {Result.class}, version = DbConfig.DATABASE_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DbConfig.DATABASE_NAME).allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public abstract CommonDao mCommonDao();
}
