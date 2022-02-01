package com.example.videoplayer

import android.content.Context

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