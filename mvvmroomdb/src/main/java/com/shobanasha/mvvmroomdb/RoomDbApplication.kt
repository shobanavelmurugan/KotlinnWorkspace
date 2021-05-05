package com.shobanasha.mvvmroomdb

import android.app.Application
import android.content.Context
import androidx.work.WorkManager

class RoomDbApplication : Application() {
    companion object {
        @JvmStatic
        var appContext: Context? = null
            private set
        var workManagerInstance: WorkManager? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        workManagerInstance = WorkManager.getInstance(appContext!!)
    }
}