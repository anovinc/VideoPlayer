package com.example.videoplayer.repository

import android.util.Log
import com.example.videoplayer.models.YoutubeVideo
import com.example.videoplayer.utils.YoutubeIdHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.example.videoplayer.BuildConfig
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirestoreRepository(private val firestoreDatabase: FirebaseFirestore) {

    suspend fun getAllVideos(collection: String) = flow {
        var videos: ArrayList<YoutubeVideo> = ArrayList()

        firestoreDatabase.collection(collection)
            .get()
            .addOnSuccessListener {
                for(item in it.documents) {
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
}