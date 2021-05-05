package com.shobanasha.mvvmroomdb.worker

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shobanasha.mvvmroomdb.model.LoginTableModel
import com.shobanasha.mvvmroomdb.repository.LoginRepository

class DbWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    var liveDataLogin: LiveData<LoginTableModel>? = null

    override fun doWork(): Result {
        val appContext = applicationContext
        liveDataLogin = LoginRepository.getLoginDetails(appContext)
        Log.w("DB Worker Result---=>", liveDataLogin!!.value.toString())
        return try {
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}