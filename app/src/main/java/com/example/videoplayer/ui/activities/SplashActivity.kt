package com.example.videoplayer.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoplayer.ui.activities.viewmodels.SplashViewModel
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val viewModel : SplashViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("isUserLoggedIn", isUserLoggedIn())
        startActivity(intent)
        finish()
    }
    private fun isUserLoggedIn(): Boolean = viewModel.isUserLoggedIn()

}