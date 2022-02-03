package com.example.videoplayer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.videoplayer.ui.authentification.UserLoginFragment

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