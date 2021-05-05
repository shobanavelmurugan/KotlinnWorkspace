package com.shobanasha.mvvmroomdb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shobanasha.mvvmroomdb.model.LoginTableModel

@Database(entities = [LoginTableModel::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun loginDao(): DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDataseClient(context: Context): LoginDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, LoginDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}