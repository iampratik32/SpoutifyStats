package com.gh0osty.spoutifystats.dashboard.topStats.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.dashboard.topStats.artists.helper.TopArtistAdapter
import com.gh0osty.spoutifystats.databinding.FragmentTopStatsArtistBinding
import com.gh0osty.spoutifystats.utilities.createToast
import com.gh0osty.spoutifystats.dashboard.topStats.helper.TopArtistPojo

class TopStatsArtistFragment : Fragment(), TopArtistStatsInterface.View {

    private lateinit var controller: TopArtistStatsInterface.Controller
    private lateinit var binding: FragmentTopStatsArtistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTopStatsArtistBinding.inflate(inflater, container, false)
        controller = TopArtistStatsController(this, requireContext())

        return binding.root
    }

    override fun setArtists(artists: List<TopArtistPojo>) {
        val recyclerView = binding.topStatsArtistRecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        val adapter = TopArtistAdapter(artists, context)
        recyclerView.adapter = adapter
    }

    override fun setTitle(title: String) {
        binding.topStatsArtistTitle.text = title
    }

    override fun createSpinnerAdapter() {
        val sAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.list_of_months,
            R.layout.spinner_center_layout
        )
        sAdapter.setDropDownViewResource(R.layout.spinner_center_layout)
        val spinner = binding.topStatsArtistSpinner
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
                    controller.getTopArtists(view.context, "long_term")
                }
                "4 Weeks" -> {
                    controller.getTopArtists(view.context, "short_term")
                }
                else -> {
                    controller.getTopArtists(view.context, "medium_term")
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
    }
}