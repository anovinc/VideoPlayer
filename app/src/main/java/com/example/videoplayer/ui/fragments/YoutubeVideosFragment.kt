package com.example.videoplayer.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.videoplayer.BuildConfig
import com.example.videoplayer.databinding.FragmentYoutubeVideosBinding
import com.example.videoplayer.ui.adapters.RecyclerViewAdapter
import com.example.videoplayer.ui.authentification.viewmodels.YoutubeVideosViewModel
import com.example.videoplayer.ui.base.BaseFragment
import com.example.videoplayer.utils.YoutubeIdHelper
import org.koin.android.ext.android.inject

class YoutubeVideosFragment : BaseFragment<FragmentYoutubeVideosBinding>(), RecyclerViewAdapter.OnVideoThumbnailClick {

    private val viewModel : YoutubeVideosViewModel by inject()
    private val recyclerViewAdapter = RecyclerViewAdapter(this)
    private val NUMBER_OF_COLS = 3

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentYoutubeVideosBinding
        get() = FragmentYoutubeVideosBinding::inflate

    override fun onPostViewCreated() {
        init()
        observeData()
        fetchData()
    }

    private fun init() {
        binding.rvVideos.apply {
            layoutManager = GridLayoutManager(requireContext(), NUMBER_OF_COLS)
            adapter = recyclerViewAdapter
        }
    }

    private fun observeData() {
        viewModel.youtubeVideos.observe(viewLifecycleOwner, {
            recyclerViewAdapter.addYoutubeVideos(it)
        })
    }

    private fun fetchData() {
        viewModel.getAllVideos()
    }

    override fun onThumbnailClick(path: String) {
        val videoViewDialog = VideoViewDialog()
        videoViewDialog.showYoutubeVideo(requireActivity(), viewLifecycleOwner, id = path)
    }
}