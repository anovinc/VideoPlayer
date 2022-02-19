package com.example.videoplayer.di

import com.example.videoplayer.repository.AuthentificationRepository
import com.example.videoplayer.repository.FirestoreRepository
import com.example.videoplayer.ui.activities.viewmodels.SplashViewModel
import com.example.videoplayer.ui.authentification.viewmodels.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModules = module {
    single { FirebaseAuth.getInstance() }
    single { Firebase.firestore }
}
val viewModelModules = module {
    single { UserRegistrationViewModel(get()) }
    single { UserLoginViewModel(get()) }
    single { SplashViewModel() }
    single { VideHostViewModel(get()) }
    single { LocalVideosViewModel() }
    single { YoutubeVideosViewModel(get()) }
}
val repositoryModules = module {
    single { AuthentificationRepository(get()) }
    single { FirestoreRepository(get()) }
}
val modules = listOf(firebaseModules, repositoryModules, viewModelModules)