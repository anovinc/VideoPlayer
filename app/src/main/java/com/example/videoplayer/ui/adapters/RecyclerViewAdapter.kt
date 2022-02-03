package com.example.videoplayer.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.videoplayer.R
import com.example.videoplayer.models.LocalVideo
import java.util.ArrayList

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.LocalVideoViewHolder> {

    constructor(onVideoThumbnailClick: OnVideoThumbnailClick) {
        this.onVideoThumbnailClick = onVideoThumbnailClick
    }

    private val localVideos: ArrayList<LocalVideo> = ArrayList<LocalVideo>()
    private var onVideoThumbnailClick: OnVideoThumbnailClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalVideoViewHolder {
        return LocalVideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_local_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocalVideoViewHolder, position: Int) {
        holder.bindVideos(localVideos[position])
    }

    override fun getItemCount(): Int {
        return localVideos.size
    }

    fun addVideos(localVideos: ArrayList<LocalVideo>?) {
        if (this.localVideos.isNotEmpty()) this.localVideos.clear()
        this.localVideos.addAll(localVideos!!)
        notifyDataSetChanged()
    }

    inner class LocalVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var thumbnail: ImageView = itemView.findViewById(R.id.iv_video_thumbnail)
        fun bindVideos(video: LocalVideo) {
            thumbnail.setImageBitmap(video.imageThumbnail)
        }

        init {
            itemView.setOnClickListener { v: View? ->
                onVideoThumbnailClick?.onThumbnailClick(
                    localVideos[adapterPosition].videoPath
                )
                Log.d("videopath", localVideos[adapterPosition].videoPath)
            }
        }
    }

    interface OnVideoThumbnailClick {
        fun onThumbnailClick(path: String?)
    }
}