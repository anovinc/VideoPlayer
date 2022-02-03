package com.example.videoplayer.di

import com.example.videoplayer.ui.authentification.viewmodels.VideHostViewModel
import com.example.videoplayer.repository.AuthentificationRepository
import com.example.videoplayer.ui.authentification.viewmodels.UserRegistrationViewModel
import com.example.videoplayer.ui.activities.viewmodels.SplashViewModel
import com.example.videoplayer.ui.authentification.viewmodels.LocalVideosViewModel
import com.example.videoplayer.ui.authentification.viewmodels.UserLoginViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseModules = module {
    single { FirebaseAuth.getInstance() }
}
val viewModelModules = module {
    single { UserRegistrationViewModel(get()) }
    single { UserLoginViewModel(get()) }
    single { SplashViewModel() }
    single { VideHostViewModel(get()) }
    single { LocalVideosViewModel() }
}
val repositoryModules = module {
    single { AuthentificationRepository(get()) }
}
val modules = listOf(firebaseModules, repositoryModules, viewModelModules)