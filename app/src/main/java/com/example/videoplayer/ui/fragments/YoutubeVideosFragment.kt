package com.example.videoplayer.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.videoplayer.databinding.FragmentYoutubeVideosBinding
import com.example.videoplayer.extensions.onClick
import com.example.videoplayer.ui.adapters.RecyclerViewAdapter
import com.example.videoplayer.ui.authentification.viewmodels.YoutubeVideosViewModel
import com.example.videoplayer.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import com.example.videoplayer.R



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
        sortVideos()
    }

    private fun init() {
        binding.rvVideos.apply {
            layoutManager = GridLayoutManager(requireContext(), NUMBER_OF_COLS)
            adapter = recyclerViewAdapter
        }
    }

    private fun observeData() {
        viewModel.youtubeVideos.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.INVISIBLE
            recyclerViewAdapter.addYoutubeVideos(it)
        })
    }

    private fun fetchData() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getAllVideos()
    }

    override fun onThumbnailClick(path: String) {
        val videoViewDialog = VideoViewDialog()
        videoViewDialog.showYoutubeVideo(requireActivity(), viewLifecycleOwner, id = path)
    }

    private fun sortVideos() {
        binding.btnCategory.onClick {
            when(binding.rgSort.checkedRadioButtonId) {
                R.id.all_videos -> fetchData()
                R.id.music -> findVideos("music")
                R.id.sport -> findVideos("sport")
                R.id.entertaiment -> findVideos("zabava")
            }
        }
    }

    private fun findVideos(id: String) {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getVideos(id)
    }


}