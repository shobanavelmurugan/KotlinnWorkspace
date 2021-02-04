package com.shobanasha.daylighttheme

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat


class MainActivityNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val switchCompat = findViewById<SwitchCompat>(R.id.switchCompat)
        val button: AppCompatButton = findViewById(R.id.button)
        button.setOnClickListener {
            AlertDialog.Builder(this@MainActivityNew, R.style.MyDialog)
                .setTitle("Title")
                .setMessage("Message")
                .show()
        }

        // Saving state of our app
        // using SharedPreferences
        // Saving state of our app
        // using SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences(
            "sharedPrefs", MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val isDarkModeOn: Boolean = sharedPreferences
            .getBoolean(
                "isDarkModeOn", false
            )

        // When user reopens the app
        // after applying dark/light mode

        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate
                .setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            switchCompat.isChecked = false

        } else {
            AppCompatDelegate
                .setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            switchCompat.isChecked = true

        }
        switchCompat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // if dark mode is on it
                // will turn it off
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_NO
                    );
                // it will set isDarkModeOn
                // boolean to false
                editor.putBoolean(
                    "isDarkModeOn", false
                );
                editor.apply();
                val intent: Intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                startActivity(intent)
            } else {
                // if dark mode is off
                // it will turn it on
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_YES
                    );

                // it will set isDarkModeOn
                // boolean to true
                editor.putBoolean(
                    "isDarkModeOn", true
                );
                editor.apply();

                val intent: Intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                startActivity(intent)
            }
        }

    }
}