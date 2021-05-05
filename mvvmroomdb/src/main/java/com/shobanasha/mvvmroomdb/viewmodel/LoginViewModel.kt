package com.shobanasha.mvvmroomdb.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shobanasha.mvvmroomdb.model.LoginTableModel
import com.shobanasha.mvvmroomdb.repository.LoginRepository

class LoginViewModel : ViewModel() {

    var liveDataLogin: LiveData<LoginTableModel>? = null

    fun insertData(context: Context, username: String, password: String) {
        LoginRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String): LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, username)
        return liveDataLogin
    }

    fun getLoginDetailsFromWorker(context: Context): LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context)
        return liveDataLogin
    }
}