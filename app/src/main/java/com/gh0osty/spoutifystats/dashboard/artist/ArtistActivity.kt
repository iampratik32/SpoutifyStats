package com.gh0osty.spoutifystats.dashboard.artist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gh0osty.spoutifystats.dashboard.artist.helper.adapters.SimilarArtistAdapter
import com.gh0osty.spoutifystats.dashboard.artist.helper.adapters.ArtistTopSongsAdapter
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.dashboard.artist.helper.pojos.ArtistPojo
import com.gh0osty.spoutifystats.dashboard.artist.helper.pojos.ArtistTopSongPojo
import com.gh0osty.spoutifystats.dashboard.topStats.artists.TopArtistStatsController
import com.gh0osty.spoutifystats.dashboard.topStats.artists.TopArtistStatsInterface
import com.gh0osty.spoutifystats.dashboard.topStats.helper.TopArtistPojo
import com.gh0osty.spoutifystats.databinding.ActivityArtistBinding
import com.gh0osty.spoutifystats.databinding.FragmentTopStatsArtistBinding
import com.gh0osty.spoutifystats.utilities.createToast
import com.gh0osty.spoutifystats.utilities.loadImage
import com.google.android.material.appbar.CollapsingToolbarLayout

class ArtistActivity : AppCompatActivity(), ArtistInterface.View {

    private lateinit var controller: ArtistInterface.Controller
    private lateinit var binding: ActivityArtistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistBinding.inflate(layoutInflater)
        controller = ArtistController(this, this)
        setContentView(binding.root)
    }

    override fun getBundle(): Bundle {
        return intent.extras!!
    }

    override fun setSimilarArtistList(artists: List<ArtistPojo>) {
        binding.artistSimilarArtistRecyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        val adapter2 = SimilarArtistAdapter(artists, this)
        binding.artistSimilarArtistRecyclerView.adapter = adapter2
    }

    override fun setTopSongList(songs: List<ArtistTopSongPojo>) {
        binding.artistTopSongsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ArtistTopSongsAdapter(songs, this)
        binding.artistTopSongsRecyclerView.adapter = adapter
    }

    override fun setInitialInfo(name: String, image: String, id: String) {
        binding.artistCollapsingLayout.title = name
        binding.artistName.text = name
        binding.artistCollapsingLayout.setExpandedTitleColor(Color.TRANSPARENT)
        loadImage(this, binding.artistBackground, image)
        binding.artistToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.artistSpotifyIcon.setOnClickListener {
            Toast.makeText(this, "Open Artist $id From Spotify", Toast.LENGTH_SHORT).show()
        }
    }

    override fun apiError(error: String) {
        createToast(this, error, false)
    }
}