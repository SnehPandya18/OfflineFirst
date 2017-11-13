package com.snehpandya.offlinefirst.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

/**
 * Created by sneh.pandya on 10/11/17.
 */

    //TODO: 05. Setup Room Database

@Database(entities = {Result.class}, version = DbConfig.DATABASE_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DbConfig.DATABASE_NAME).addMigrations(MIGRATION_1_2).allowMainThreadQueries().build();
            }

            return INSTANCE;
        }
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Result " + "ADD COLUMN picture TEXT");
        }
    };

    public abstract CommonDao mCommonDao();
}
