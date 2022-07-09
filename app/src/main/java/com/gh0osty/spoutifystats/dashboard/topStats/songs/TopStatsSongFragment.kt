package com.gh0osty.spoutifystats.dashboard.topStats.songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.dashboard.topStats.songs.helper.TopSongAdapter
import com.gh0osty.spoutifystats.databinding.FragmentTopStatsSongBinding
import com.gh0osty.spoutifystats.utilities.createToast
import com.gh0osty.spoutifystats.dashboard.topStats.songs.helper.TopSongPojo

class TopStatsSongFragment : Fragment(), TopSongStatsInterface.View {

    private lateinit var controller: TopSongStatsInterface.Controller
    private lateinit var binding: FragmentTopStatsSongBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTopStatsSongBinding.inflate(inflater, container, false)
        controller = TopSongsStatsController(this, requireContext())

        return binding.root
    }

    override fun setSongs(songs: List<TopSongPojo>) {
        val recyclerView = binding.topStatsSongRecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        val adapter = TopSongAdapter(songs, context)
        recyclerView.adapter = adapter
    }

    override fun setTitle(title: String) {
        binding.topStatsSongTitle.text = title
    }

    override fun createSpinnerAdapter() {
        val sAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.list_of_months,
            R.layout.spinner_center_layout
        )
        sAdapter.setDropDownViewResource(R.layout.spinner_center_layout)
        val spinner = binding.topStatsSongSpinner
        spinner.adapter = sAdapter
        spinner.onItemSelectedListener = SpinnerHelper()
    }

    override fun apiError(error: String) {
        createToast(requireContext(), error, false)
    }

    inner class SpinnerHelper : AdapterView.OnItemSelectedListener,
        AdapterView.OnItemClickListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            when (parent.getItemAtPosition(position).toString()) {
                "All Time" -> {
                    controller.getTopSongs(view.context, "long_term")
                }
                "4 Weeks" -> {
                    controller.getTopSongs(view.context, "short_term")
                }
                else -> {
                    controller.getTopSongs(view.context, "medium_term")
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
    }
}