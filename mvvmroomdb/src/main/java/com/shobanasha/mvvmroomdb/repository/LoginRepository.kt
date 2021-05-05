package com.shobanasha.mvvmroomdb.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.shobanasha.mvvmroomdb.model.LoginTableModel
import com.shobanasha.mvvmroomdb.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginRepository {

    companion object {

        var loginDatabase: LoginDatabase? = null

        var loginTableModel: LiveData<LoginTableModel>? = null

        fun initializeDB(context: Context): LoginDatabase {
            return LoginDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {
            try {
                loginDatabase = initializeDB(context)

                CoroutineScope(IO).launch {
                    val loginDetails = LoginTableModel(username, password)
                    var insertValue = loginDatabase!!.loginDao().InsertData(loginDetails)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        fun getLoginDetails(context: Context, username: String): LiveData<LoginTableModel>? {

            loginDatabase = initializeDB(context)

            loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)

            return loginTableModel
        }

        fun getLoginDetails(context: Context): LiveData<LoginTableModel>? {

            loginDatabase = initializeDB(context)

            loginTableModel = loginDatabase!!.loginDao().getLoginDetails()

            return loginTableModel
        }
    }
}