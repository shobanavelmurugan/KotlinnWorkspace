package com.shobanasha.daylighttheme

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class InitApplication : Application() {
    val NIGHT_MODE = "NIGHT_MODE"
    private var isNightModeEnabled = false
    private var mContext: Context? = null
    private var singleton: InitApplication? = null

    fun getInstance(): InitApplication? {
        if (singleton == null) {
            singleton = InitApplication()
        }
        return singleton
    }

    fun getContext(): Context? {
        return mContext
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        singleton = this
        val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        isNightModeEnabled = mPrefs.getBoolean(NIGHT_MODE, false)
    }

    fun isNightModeEnabled(): Boolean {
        return isNightModeEnabled
    }

    fun setIsNightModeEnabled(isNightModeEnabled: Boolean) {
        this.isNightModeEnabled = isNightModeEnabled
        val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: SharedPreferences.Editor = mPrefs.edit()
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled)
        editor.apply()

    }
}