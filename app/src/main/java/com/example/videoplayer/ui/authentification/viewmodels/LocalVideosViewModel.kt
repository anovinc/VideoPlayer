package com.example.videoplayer.ui.authentification.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videoplayer.models.LocalVideo

class LocalVideosViewModel : ViewModel() {
    private var localVideos : MutableLiveData<ArrayList<LocalVideo>> = MutableLiveData()

    fun getAllVideos(context: Context) {
        val localVideoArrayList = java.util.ArrayList<LocalVideo>()
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val count: Int
        val projection = arrayOf(MediaStore.Video.Media._ID)
        val columns = arrayOf(MediaStore.Video.VideoColumns.DATA, MediaStore.Video.VideoColumns.DISPLAY_NAME)
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        count = cursor!!.count
        Log.d("kruzorooz", cursor.count.toString())

        for (i in count - 1 downTo 0) {
            cursor.moveToPosition(i)
            val data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA))
            val thumbnail = ThumbnailUtils.createVideoThumbnail(data, MediaStore.Video.Thumbnails.MINI_KIND)
            localVideoArrayList.add(LocalVideo(data, thumbnail!!))
        }
        localVideos.postValue(localVideoArrayList)
        cursor.close()
    }

    fun getLocalVideos() : MutableLiveData<ArrayList<LocalVideo>> {
        return localVideos
    }
}