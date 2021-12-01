package com.gh0osty.spoutifystats.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.Adapters.TopArtistAdapter
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.ViewModels.TopArtistViewModel

class TopStats_ArtistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_top_stats__artist, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.topStatsArtist_recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity,3)
        val data = ArrayList<TopArtistViewModel>()
        for (i in 0..50) {
            data.add(TopArtistViewModel("A${i}","Artist ${i+1}",R.drawable.spotify))
        }
        val adapter = TopArtistAdapter(data)
        recyclerView.adapter = adapter


        return view
    }
}