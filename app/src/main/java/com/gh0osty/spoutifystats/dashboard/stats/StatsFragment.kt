package com.gh0osty.spoutifystats.dashboard.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gh0osty.spoutifystats.R

class StatsFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val mainView: View = inflater.inflate(R.layout.fragment_stats, container, false)



        return mainView
    }


}