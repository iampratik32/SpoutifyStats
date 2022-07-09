package com.gh0osty.spoutifystats.dashboard.topStats.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gh0osty.spoutifystats.dashboard.topStats.artists.TopStatsArtistFragment
import com.gh0osty.spoutifystats.dashboard.topStats.songs.TopStatsSongFragment

class TopStatsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TopStatsArtistFragment()
            }
            1 -> {
                TopStatsSongFragment()
            }
            else -> {
                null!!
            }
        }
    }


}