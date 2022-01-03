package com.gh0osty.spoutifystats.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.Adapters.TopArtistAdapter
import com.gh0osty.spoutifystats.Adapters.TopSongAdapter
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.ViewModels.TopArtistViewModel
import com.gh0osty.spoutifystats.ViewModels.TopSongViewModel

class TopStats_SongFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_top_stats__song, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.topStatsSong_recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        val data = ArrayList<TopSongViewModel>()
        for (i in 0..50) {
            data.add(TopSongViewModel("S${i}","Artist ${i+1}","Song ${i+1}",R.drawable.spotify))
        }
        val adapter = TopSongAdapter(data,context)
        recyclerView.adapter = adapter

        return view

    }


}