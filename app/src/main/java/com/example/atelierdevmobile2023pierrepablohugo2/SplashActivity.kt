package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.atelierdevmobile2023pierrepablohugo2.Model.UserStorage

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            val retrievedObject = UserStorage.getUser(this)

            val newIntent: Intent

            if (retrievedObject != null) {
                newIntent=Intent(application, MainActivity::class.java)
                startActivity(newIntent)
                finish()
            } else {
                newIntent=Intent(application, AccountActivity::class.java)
                startActivity(newIntent)
                finish()
            }
        },1500)
    }
}