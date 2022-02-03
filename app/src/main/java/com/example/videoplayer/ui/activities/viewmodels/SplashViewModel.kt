package com.example.videoplayer.ui.activities.viewmodels

import androidx.lifecycle.ViewModel
import com.example.videoplayer.utils.PrefsManager

class SplashViewModel: ViewModel() { fun isUserLoggedIn() : Boolean {
    return !PrefsManager().getUser().isNullOrEmpty()
}
}