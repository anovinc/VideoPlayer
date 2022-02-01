package com.example.videoplayer.ui.authentification.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.repository.AuthentificationRepository
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val authenticationRepository: AuthentificationRepository) : ViewModel() {

    fun registerUser(username: String, password: String,onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            authenticationRepository.register(username, password, onResult)
        }
    }
    fun isMailOrPasswordEmpty(email: String, password: String): Boolean = (email.isNotEmpty() && password.isNotEmpty())

}