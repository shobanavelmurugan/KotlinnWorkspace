package com.shobanasha.daylighttheme

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (InitApplication().getInstance()!!.isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main)
        val switchCompat = findViewById<SwitchCompat>(R.id.switchCompat)
        val button: AppCompatButton = findViewById(R.id.button)
        button.setOnClickListener {
            AlertDialog.Builder(this@MainActivity, R.style.MyDialog)
                .setTitle("Title")
                .setMessage("Message")
                .show()
        }
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) switchCompat.isChecked =
            true
        switchCompat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                InitApplication().getInstance()!!.setIsNightModeEnabled(true)
                val intent: Intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                startActivity(intent)
            } else {
                InitApplication().getInstance()!!.setIsNightModeEnabled(false)
                val intent: Intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                startActivity(intent)
            }
        }


    }
}