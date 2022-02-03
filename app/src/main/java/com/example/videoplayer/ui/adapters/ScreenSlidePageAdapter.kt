package com.example.videoplayer.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.videoplayer.ui.fragments.LocalVideosFragment
import com.example.videoplayer.ui.authentification.UserLoginFragment
import com.example.videoplayer.ui.fragments.YoutubeVideosFragment

class ScreenSlidePageAdapter(fragmentActivity: Fragment) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LocalVideosFragment()
            1 -> YoutubeVideosFragment()
            else -> UserLoginFragment()
        }
    }

}