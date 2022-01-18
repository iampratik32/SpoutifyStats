package com.gh0osty.spoutifystats.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.Adapters.TopArtistAdapter
import com.gh0osty.spoutifystats.Adapters.TopSongAdapter
import com.gh0osty.spoutifystats.Controllers.StatsController
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.ViewModels.TopArtistViewModel
import com.gh0osty.spoutifystats.ViewModels.TopSongViewModel
import org.json.JSONObject

class TopStats_SongFragment : Fragment() {

    var statsController = StatsController()
    var title: TextView? = null
    var spinner: Spinner? = null
    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_top_stats__song, container, false)
        title = view.findViewById(R.id.topStatsSong_title)
        spinner = view.findViewById(R.id.topStatsSong_spinner)

        recyclerView = view.findViewById(R.id.topStatsSong_recyclerView)
        recyclerView?.layoutManager = GridLayoutManager(activity, 3)

        val sAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            view.context,
            R.array.list_of_months,
            R.layout.spinner_center_layout
        )
        sAdapter.setDropDownViewResource(R.layout.spinner_center_layout)
        spinner?.adapter = sAdapter
        spinner?.onItemSelectedListener = SpinnerClass()

        return view

    }

    private fun getArtists(
        context: Context,
        term: String,
        which: String,
        recyclerView: RecyclerView
    ) {
        when (term) {
            "long_term" -> {
                title?.text = context.getString(R.string.topSong3)
            }
            "short_term" -> {
                title?.text = context.getString(R.string.topSong1)
            }
            else -> {
                title?.text = context.getString(R.string.topSong2)
            }
        }
        statsController.getTop(context, term, which, object : StatsController.TopArtistListener {
            override fun onError(message: String?, code: Int?) {
                message?.let { Log.d("ERROR HERE", message) }
            }

            override fun onResponse(response: Any?, what: Boolean) {

                if (what) {
                    getArtists(context, term, which, recyclerView)
                } else {
                    val obj = JSONObject(response as String)
                    val items = obj.getJSONArray("items")
                    val data = ArrayList<TopSongViewModel>()
                    for (i in 0 until items.length()) {
                        val first = items.getJSONObject(i)
                        data.add(
                            TopSongViewModel(
                                first.getString("id"),
                                first.getJSONArray("artists").getJSONObject(0).getString("name"),
                                first.getString("name"),
                                first.getJSONObject("album").getJSONArray("images").getJSONObject(1).getString("url")
                                )

                        )
                        val adapter = TopSongAdapter(data, context)
                        recyclerView.adapter = adapter
                    }
                }
            }
        })
    }

    inner class SpinnerClass : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            when (parent.getItemAtPosition(position).toString()) {
                "All Time" -> {
                    recyclerView?.let { getArtists(view.context, "long_term", "tracks", it) }
                }
                "4 Weeks" -> {
                    recyclerView?.let { getArtists(view.context, "short_term", "tracks", it) }
                }
                else -> {
                    recyclerView?.let { getArtists(view.context, "medium_term", "tracks", it) }
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
    }


}