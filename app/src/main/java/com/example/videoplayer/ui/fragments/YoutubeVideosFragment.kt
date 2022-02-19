package com.example.videoplayer.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.videoplayer.BuildConfig
import com.example.videoplayer.databinding.FragmentYoutubeVideosBinding
import com.example.videoplayer.ui.base.BaseFragment

class YoutubeVideosFragment : BaseFragment<FragmentYoutubeVideosBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentYoutubeVideosBinding
        get() = FragmentYoutubeVideosBinding::inflate

    override fun onPostViewCreated() {
    }
}