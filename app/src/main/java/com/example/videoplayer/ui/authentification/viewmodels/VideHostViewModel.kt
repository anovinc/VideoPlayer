package com.example.videoplayer.ui.authentification.viewmodels

import androidx.lifecycle.ViewModel
import com.example.videoplayer.repository.AuthentificationRepository

class VideHostViewModel(private val authenticationRepository: AuthentificationRepository) : ViewModel() {

    fun signOutUser() {
        authenticationRepository.signOut()
    }
    fun getCurrentUser() {
        authenticationRepository.getUserName()
    }
    fun saveUser() {
    }

}