package com.example.todoapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todoapp.MyDatabase
import com.example.todoapp.R
import com.example.todoapp.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startHomeActivity()

    }

    private fun startHomeActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
                           val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)

        },2000)
    }
}