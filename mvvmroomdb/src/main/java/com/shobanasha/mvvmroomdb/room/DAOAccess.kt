package com.shobanasha.mvvmroomdb.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shobanasha.mvvmroomdb.model.LoginTableModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: LoginTableModel)

    /* @Query("SELECT * FROM Login WHERE Username =:username")*/
    @Query("SELECT * FROM Login")
    fun getLoginDetails(): LiveData<LoginTableModel>

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?): LiveData<LoginTableModel>
}