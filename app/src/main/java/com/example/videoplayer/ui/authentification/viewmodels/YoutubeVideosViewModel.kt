package com.example.videoplayer.ui.authentification.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.BuildConfig
import com.example.videoplayer.models.YoutubeVideo
import com.example.videoplayer.repository.FirestoreRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class YoutubeVideosViewModel(private val firestoreRepository: FirestoreRepository) : ViewModel() {

    private val collectionName = "videos"
    private var _youtubeVideos: MutableLiveData<ArrayList<YoutubeVideo>> = MutableLiveData()
    var youtubeVideos: LiveData<ArrayList<YoutubeVideo>> = _youtubeVideos

    fun getAllVideos() {
        viewModelScope.launch {
            firestoreRepository.getAllVideos(collection = collectionName).collect {
                _youtubeVideos.postValue(it)
            }
        }
    }
    fun saveVideo(name : String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            firestoreRepository.saveVideo(collectionName, name, onResult)
        }
    }
}