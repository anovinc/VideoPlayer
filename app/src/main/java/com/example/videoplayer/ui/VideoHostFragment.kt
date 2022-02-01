package com.example.videoplayer.ui

import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.videoplayer.R
import com.example.videoplayer.VideHostViewModel
import com.example.videoplayer.databinding.FragmentVideoHostBinding
import org.koin.android.ext.android.inject

class VideoHostFragment : BaseFragment<FragmentVideoHostBinding>() {
    private val viewModel: VideHostViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentVideoHostBinding
        get() = FragmentVideoHostBinding::inflate

    override fun onPostViewCreated() {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sign_out -> {
                signOut()
                true
            }
            else -> false

        }
    }


    private fun signOut() {
        viewModel.signOutUser()
        findNavController().navigate(R.id.action_videoHostFragment_to_userLoginFragment)
    }

}