package com.gh0osty.spoutifystats.dashboard.song

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.dashboard.song.helper.adapters.SongArtistAdapter
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.dashboard.song.helper.pojos.SongArtistPojo
import com.google.android.material.appbar.CollapsingToolbarLayout

class SongActivity : AppCompatActivity() {

    private var collapsingLayout: CollapsingToolbarLayout? = null
    private var toolbar: Toolbar? = null
    private var nameTextView: TextView? = null
    private var spotifyIcon: ImageView? = null
    private var imageBackground: ImageView? = null
    private var artistRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        val bundle = intent.extras

        val image = bundle!!.getString("sImage", "")
        val name = bundle.getString("sName", "")
        val id = bundle.getString("sId", "")

        fetchAll()

        artistRecyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        val data = ArrayList<SongArtistPojo>()
        for (i in 1..2) {
            data.add(SongArtistPojo("A${i}","Artist $i",R.drawable.spotify))
        }
        val adapter = SongArtistAdapter(data, this)
        artistRecyclerView?.adapter = adapter

        collapsingLayout?.title = name
        nameTextView?.text = name
        collapsingLayout?.setExpandedTitleColor(Color.TRANSPARENT)

        toolbar?.setNavigationOnClickListener {
            finish()
        }

        spotifyIcon?.setOnClickListener {
            Toast.makeText(this, "Open Song $id From Spotify", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fetchAll() {
        collapsingLayout = findViewById(R.id.song_collapsingLayout)
        toolbar = findViewById(R.id.song_toolBar)
        nameTextView = findViewById(R.id.song_name)
        spotifyIcon = findViewById(R.id.song_spotifyIcon)
        imageBackground = findViewById(R.id.song_background)
        artistRecyclerView = findViewById(R.id.song_artistsRecyclerView)
    }
}