package com.example.camerax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        if (null == savedInstanceState) {
            fragmentManager.beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance())
                .commit()
        }
    }
}