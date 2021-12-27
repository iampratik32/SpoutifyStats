package com.gh0osty.spoutifystats.Screens

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.Adapters.Artist_TopSongsAdapter
import com.gh0osty.spoutifystats.Adapters.TopArtistAdapter
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.ViewModels.Artist_TopSongsViewModel
import com.gh0osty.spoutifystats.ViewModels.TopArtistViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout

class ArtistActivity : AppCompatActivity() {

    private var collapsingLayout: CollapsingToolbarLayout? = null
    private var toolbar: Toolbar? = null
    private var nameTextView: TextView? = null
    private var spotifyIcon: ImageView? = null
    private var imageBackground: ImageView? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        val bundle = intent.extras

        val image = bundle!!.getString("aImage", "")
        val name = bundle.getString("aName", "")
        val id = bundle.getString("aId", "")

        fetchAll()

        recyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val data = ArrayList<Artist_TopSongsViewModel>()
        for (i in 1..10) {
            data.add(Artist_TopSongsViewModel("S${i}","Song $i",i,R.drawable.spotify))
        }
        val adapter = Artist_TopSongsAdapter(data,this)
        recyclerView?.adapter = adapter

        collapsingLayout?.title = name
        nameTextView?.text = name
        collapsingLayout?.setExpandedTitleColor(Color.TRANSPARENT)

        toolbar?.setNavigationOnClickListener {
            finish()
        }

        spotifyIcon?.setOnClickListener {
            Toast.makeText(this, "Open Artist $id From Spotify", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fetchAll() {
        collapsingLayout = findViewById(R.id.artist_collapsingLayout)
        toolbar = findViewById(R.id.artist_toolBar)
        nameTextView = findViewById(R.id.artist_name)
        spotifyIcon = findViewById(R.id.artist_spotifyIcon)
        imageBackground = findViewById(R.id.artist_background)
        recyclerView = findViewById(R.id.artist_topSongsRecyclerView)
    }
}