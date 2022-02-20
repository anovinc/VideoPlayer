package com.example.videoplayer.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.videoplayer.databinding.FragmentLocalVideosBinding
import com.example.videoplayer.ui.base.BaseFragment
import com.example.videoplayer.ui.adapters.RecyclerViewAdapter
import com.example.videoplayer.ui.authentification.viewmodels.LocalVideosViewModel
import org.koin.android.ext.android.inject

class LocalVideosFragment : BaseFragment<FragmentLocalVideosBinding>(), RecyclerViewAdapter.OnVideoThumbnailClick {

    private val viewModel : LocalVideosViewModel by inject()
    private val recyclerViewAdapter = RecyclerViewAdapter(this)
    private val NUMBER_OF_COLS = 3

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLocalVideosBinding
        get() = FragmentLocalVideosBinding::inflate

    override fun onPostViewCreated() {
        checkPermissions()
        init()
        observeData()
    }

    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            fetchLocalVideos()
        else
            binding.tvPermissions.visibility = View.VISIBLE
    }
    private fun init() {
        recyclerViewAdapter.setIsLocal(true)
        binding.rvVideos.apply {
            layoutManager = GridLayoutManager(requireContext(), NUMBER_OF_COLS)
            adapter = recyclerViewAdapter
        }
    }
    private fun fetchLocalVideos() {
        viewModel.getVideos(requireContext())
    }
    private fun observeData() {
        viewModel.getLocalVideos().observe(viewLifecycleOwner, {
            recyclerViewAdapter.addLocalVideos(it)
        })
    }

    override fun onThumbnailClick(path: String) {
        val videoViewDialog = VideoViewDialog()
        videoViewDialog.showLocalVideo(requireActivity(), path)
    }
}
