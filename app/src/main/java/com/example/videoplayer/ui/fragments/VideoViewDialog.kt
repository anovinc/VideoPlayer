package com.example.videoplayer.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.net.Uri
import android.view.Window
import android.widget.VideoView
import com.example.videoplayer.R

class VideoViewDialog {
    fun showVideoView(activity: Activity, path: String) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.layout_video_dialog_v2)
        val videoView = dialog.findViewById<VideoView>(R.id.vw_video)
        videoView.setVideoURI(Uri.parse(path))

        dialog.show()
        videoView.start()
    }
}