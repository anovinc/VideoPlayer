package com.example.videoplayer.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.videoplayer.databinding.FragmentAdminVideosBinding
import com.example.videoplayer.ui.authentification.viewmodels.YoutubeVideosViewModel
import com.example.videoplayer.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class AdminVideosFragment : BaseFragment<FragmentAdminVideosBinding>() {

    private val viewModel: YoutubeVideosViewModel by inject()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAdminVideosBinding
        get() = FragmentAdminVideosBinding::inflate

    override fun onPostViewCreated() {
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        with(binding) {
            btnSaveVideo.setOnClickListener {

            }
        }
    }
}