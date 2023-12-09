package com.example.tracto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Start your main activity here
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)

    }
}