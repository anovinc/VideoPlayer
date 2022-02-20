package com.example.videoplayer.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.videoplayer.R
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
                binding.progressBar.visibility = View.VISIBLE
                if(binding.urlInput.text.toString().isEmpty() || !isYoutubeUrl(binding.urlInput.text.toString())) {
                    Toast.makeText(context, "Neispravan unos.", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.INVISIBLE
                }
                 else viewModel.saveVideo(binding.urlInput.text.toString(), getCategory()) {
                    if (it) {
                        Toast.makeText(context,"Dodan novi YT video!!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "Neuspješno dodavanje", Toast.LENGTH_SHORT).show()
                    }
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun isYoutubeUrl(url: String): Boolean {
        val pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+"
        if(url.isNotEmpty() && url.matches(pattern.toRegex())) {
            return true
        }
        return false
    }

    private fun getCategory(): String {
        return when (binding.rgOption.checkedRadioButtonId) {
            R.id.sport -> "sport"
            R.id.entertaiment -> "zabava"
            R.id.music -> "glazba"
            else  -> ""
        }
    }


}