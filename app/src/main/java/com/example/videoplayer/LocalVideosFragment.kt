package com.example.videoplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.videoplayer.databinding.FragmentLocalVideosBinding
import com.example.videoplayer.databinding.FragmentVideoHostBinding
import com.example.videoplayer.ui.BaseFragment

class LocalVideosFragment : BaseFragment<FragmentLocalVideosBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLocalVideosBinding
        get() = FragmentLocalVideosBinding::inflate

    override fun onPostViewCreated() {
    }
}