package com.example.videoplayer.repository

import android.util.Log
import com.example.videoplayer.models.YoutubeVideo
import com.example.videoplayer.utils.YoutubeIdHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.example.videoplayer.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirestoreRepository(private val firestoreDatabase: FirebaseFirestore) {

    suspend fun getAllVideos(collection: String) = flow {
        var videos: ArrayList<YoutubeVideo> = ArrayList()

        firestoreDatabase.collection(collection)
            .get()
            .addOnSuccessListener {
                for (item in it.documents) {
                    val id = YoutubeIdHelper.getYouTubeId(item[BuildConfig.VIDEO_URL] as String)
                    val thumbnail = YoutubeIdHelper.getImageUrlFromId(id)

                    videos.add(YoutubeVideo(id, thumbnail))
                }
            }
            .addOnFailureListener {
                Log.e("getAllVideos", "Nekaj nevala")
            }
            .await()

        emit(videos)
    }

    suspend fun getVideos(id: String, collection: String) = flow {
        var videos: ArrayList<YoutubeVideo> = ArrayList()

        firestoreDatabase.collection(collection)
            .whereEqualTo("id", id)
            .get()
            .addOnSuccessListener {
                for (item in it.documents) {
                    val videoId = YoutubeIdHelper.getYouTubeId(item[BuildConfig.VIDEO_URL] as String)
                    val thumbnail = YoutubeIdHelper.getImageUrlFromId(videoId)

                    videos.add(YoutubeVideo(videoId, thumbnail))
                }
            }
            .addOnFailureListener {
                Log.e("getAllVideos", "Nekaj nevala")
            }
            .await()

        emit(videos)
    }

    suspend fun saveVideo(collection: String, name: String,id : String, onResult: (Boolean) -> Unit) {
        withContext(Dispatchers.IO) {
            val map = hashMapOf(
                "videoURL" to name,
                "id" to id
            )
            firestoreDatabase.collection(collection).document().set(map).addOnSuccessListener {
                onResult(true)
            }
                .addOnFailureListener {
                    onResult(false)
                }
        }

    }
}