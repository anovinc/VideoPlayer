package com.example.videoplayer.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.VideoView
import androidx.lifecycle.LifecycleOwner
import com.example.videoplayer.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoViewDialog {

    fun showLocalVideo(activity: Activity, path: String) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.layout_video_dialog_v2)
        dialog.findViewById<YouTubePlayerView>(R.id.youtubeVideoPlayer).visibility = View.GONE
        val videoView = dialog.findViewById<VideoView>(R.id.vw_video)
        videoView.visibility = View.VISIBLE
        videoView.setVideoURI(Uri.parse(path))

        dialog.show()
        videoView.start()
    }

    fun showYoutubeVideo(activity: Activity, lifecycleOwner: LifecycleOwner, path: String) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.layout_video_dialog_v2)
        dialog.findViewById<VideoView>(R.id.vw_video).visibility = View.GONE

        val youTubePlayerView = dialog.findViewById<YouTubePlayerView>(R.id.youtubeVideoPlayer)
        youTubePlayerView.visibility = View.VISIBLE

        lifecycleOwner.lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(path, 0f)
            }
        })
        dialog.show()
    }
}