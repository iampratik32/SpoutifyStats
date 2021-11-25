package com.gh0osty.spoutifystats.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.gh0osty.spoutifystats.Adapters.TopStatsAdapter
import com.gh0osty.spoutifystats.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TopStatsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_top_stats, container, false)

        val tabLayout: TabLayout = view.findViewById(R.id.topStats_tabLayout)
        val viewPager: ViewPager2 = view.findViewById(R.id.topStats_viewPager)
        viewPager.adapter = TopStatsAdapter(requireActivity())
        TabLayoutMediator(
            tabLayout,  viewPager
        ) { tab, position ->
            viewPager.setCurrentItem(0, true)
            when (position) {
                0 -> {
                    tab.setText(R.string.artists)
                }
                1 -> {
                    tab.setText(R.string.songs)
                }
            }
        }.attach()

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        return view
    }

}