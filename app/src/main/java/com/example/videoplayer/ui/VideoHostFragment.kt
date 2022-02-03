package com.example.videoplayer.ui

import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.videoplayer.R
import com.example.videoplayer.ScreenSlidePageAdapter
import com.example.videoplayer.VideHostViewModel
import com.example.videoplayer.databinding.FragmentVideoHostBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject

class VideoHostFragment : BaseFragment<FragmentVideoHostBinding>() {
    private var names = listOf<String>("local", "youtube")
    private lateinit var viewPager: ViewPager2
    private val viewModel: VideHostViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentVideoHostBinding
        get() = FragmentVideoHostBinding::inflate

    override fun onPostViewCreated() {
        viewPager = binding.pager
        val tabs = binding.tabs
        val pageAdapter = ScreenSlidePageAdapter(this)
        viewPager.adapter = pageAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = names[position]
        }.attach()
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