package com.example.navigationsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import kotlinx.android.synthetic.main.activity_screen_one.*

class ScreenTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_two)
        button.setOnClickListener {
            val activityNavigator = ActivityNavigator(this)
            activityNavigator.navigate(
                activityNavigator.createDestination().setIntent(
                    Intent(
                        this,
                        ScreenThree::class.java
                    )
                ), null, null, null
            )
        }
    }
}