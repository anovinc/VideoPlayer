package com.example.videoplayer.ui.authentification.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.repository.AuthentificationRepository
import kotlinx.coroutines.launch

class UserLoginViewModel(private val authentificationRepository: AuthentificationRepository) : ViewModel() {

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            authentificationRepository.login(email,password,onResult)
        }
    }
    fun getCurrentUser(): String? = authentificationRepository.getUserName()
    fun isMailOrPasswordEmpty(email: String, password: String): Boolean = (email.isNotEmpty() && password.isNotEmpty())

}