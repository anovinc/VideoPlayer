package com.example.videoplayer.ui.authentification.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.models.LocalVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalVideosViewModel : ViewModel() {
    private var localVideos : MutableLiveData<ArrayList<LocalVideo>> = MutableLiveData()

    fun getVideos(context: Context) {
        viewModelScope.launch {
            getAllVideos(context)
        }
    }

    suspend fun getAllVideos(context: Context) {
        withContext(Dispatchers.IO) {
            val localVideoArrayList = java.util.ArrayList<LocalVideo>()
            val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            val count: Int
            val projection = arrayOf(MediaStore.Video.Media._ID)
            val columns = arrayOf(
                MediaStore.Video.VideoColumns.DATA,
                MediaStore.Video.VideoColumns.DISPLAY_NAME
            )
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            count = cursor!!.count

            for (i in count - 1 downTo 0) {
                cursor.moveToPosition(i)
                val colIndex = cursor.getColumnIndex(MediaStore.Video.Media.DATA)
                val data = cursor.getString(colIndex)
                val thumbnail = uri.toString()
                localVideoArrayList.add(LocalVideo(data, thumbnail))
            }
            localVideos.postValue(localVideoArrayList)
            cursor.close()
        }
    }

    fun getLocalVideos() : MutableLiveData<ArrayList<LocalVideo>> {
        return localVideos
    }
}