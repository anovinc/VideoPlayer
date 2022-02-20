package com.example.videoplayer.ui.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoplayer.R
import com.example.videoplayer.models.LocalVideo
import com.example.videoplayer.models.YoutubeVideo
import java.io.File
import java.util.ArrayList

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.LocalVideoViewHolder> {

    constructor(onVideoThumbnailClick: OnVideoThumbnailClick) {
        this.onVideoThumbnailClick = onVideoThumbnailClick
    }

    private val localVideos: ArrayList<LocalVideo> = ArrayList<LocalVideo>()
    private val youtubeVideos: ArrayList<YoutubeVideo> = ArrayList<YoutubeVideo>()
    private var onVideoThumbnailClick: OnVideoThumbnailClick? = null
    private var isLocal = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalVideoViewHolder {
        return LocalVideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_local_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocalVideoViewHolder, position: Int) {
        if(isLocal)
            holder.bindLocalVideos(localVideos[position])
        else
            holder.bindYoutubeVideos(youtubeVideos[position])
    }

    override fun getItemCount(): Int {
        return if(isLocal)
            localVideos.size
        else
            youtubeVideos.size
    }

    fun addLocalVideos(localVideos: ArrayList<LocalVideo>?) {
        if (this.localVideos.isNotEmpty()) this.localVideos.clear()
        this.localVideos.addAll(localVideos!!)
        notifyDataSetChanged()
    }

    fun addYoutubeVideos(youtubeVideos: ArrayList<YoutubeVideo>) {
        if(this.youtubeVideos.isNotEmpty()) this.youtubeVideos.clear()
        this.youtubeVideos.addAll(youtubeVideos)
        notifyDataSetChanged()
    }

    fun setIsLocal(isLocal: Boolean) {
        this.isLocal = isLocal
    }

    inner class LocalVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var thumbnail: ImageView = itemView.findViewById(R.id.iv_video_thumbnail)

        fun bindLocalVideos(video: LocalVideo) {
            Glide.with(thumbnail.context)
                .load(video.videoPath)
                .into(thumbnail)
        }

        fun bindYoutubeVideos(video: YoutubeVideo) {
            Glide.with(thumbnail.context)
                .load(video.videoThumbnail)
                .into(thumbnail)
        }

        init {
            itemView.setOnClickListener {
                if(isLocal) {
                    onVideoThumbnailClick?.onThumbnailClick(
                        localVideos[absoluteAdapterPosition].videoPath
                    )
                    Log.d("videopath", localVideos[absoluteAdapterPosition].videoPath)
                }
                else {
                    onVideoThumbnailClick?.onThumbnailClick(
                        youtubeVideos[absoluteAdapterPosition].videoId
                    )
                    Log.d("videopath", youtubeVideos[absoluteAdapterPosition].videoId)
                }
            }
        }
    }

    interface OnVideoThumbnailClick {
        fun onThumbnailClick(path: String)
    }
}