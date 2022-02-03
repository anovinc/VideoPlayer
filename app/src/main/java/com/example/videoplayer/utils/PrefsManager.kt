package com.example.videoplayer.utils

import android.content.Context
import com.example.videoplayer.VideoPlayerApp

class PrefsManager {
    private val sharedPreferences =
        VideoPlayerApp.application.getSharedPreferences("BelotGameSettings", Context.MODE_PRIVATE)

    private val userEditor = sharedPreferences.edit()


    fun saveUser(user: String) {
        userEditor.putString("user", user)
        userEditor.apply()
    }

    fun getUser() = sharedPreferences.getString("user", "")
}