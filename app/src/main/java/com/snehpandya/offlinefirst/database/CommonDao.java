package com.snehpandya.offlinefirst.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sneh.pandya on 10/11/17.
 */

@Dao
public interface CommonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(Result result);

    //Query for whole table
    @Query("SELECT * FROM " + DbConfig.TABLE_NAME)
    LiveData<List<Result>> getAllUsers();

    //Query to delete latest entry
    @Query("DELETE FROM " + DbConfig.TABLE_NAME + " WHERE ID = (SELECT MAX(id) FROM " + DbConfig.TABLE_NAME + ")")
    void deleteSingleUser();

    //Query to delete all entries
    @Query("DELETE FROM " + DbConfig.TABLE_NAME)
    void deleteAllUsers();
}
