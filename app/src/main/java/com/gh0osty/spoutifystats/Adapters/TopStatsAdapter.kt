package com.gh0osty.spoutifystats.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gh0osty.spoutifystats.Fragments.TopStats_ArtistFragment
import com.gh0osty.spoutifystats.Fragments.TopStats_SongFragment

class TopStatsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TopStats_ArtistFragment()
            }
            1 -> {
                TopStats_SongFragment()
            }
            else -> {
                null!!
            }
        }
    }


}