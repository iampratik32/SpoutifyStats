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
import com.gh0osty.spoutifystats.dashboard.artist.helper.pojos.ArtistTopSongPojo
import com.gh0osty.spoutifystats.dashboard.topStats.helper.TopArtistPojo
import com.google.android.material.appbar.CollapsingToolbarLayout

class ArtistActivity : AppCompatActivity() {

    private var collapsingLayout: CollapsingToolbarLayout? = null
    private var toolbar: Toolbar? = null
    private var nameTextView: TextView? = null
    private var spotifyIcon: ImageView? = null
    private var imageBackground: ImageView? = null
    private var songRecyclerView: RecyclerView? = null
    private var artistRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        val bundle = intent.extras

        val image = bundle!!.getString("aImage", "")
        val name = bundle.getString("aName", "")
        val id = bundle.getString("aId", "")

        fetchAll()
        loadArtist()

        imageBackground?.let { Glide.with(this).load(image).into(it) }

        songRecyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val data = ArrayList<ArtistTopSongPojo>()
        for (i in 1..10) {
            data.add(ArtistTopSongPojo("S${i}","Song $i",i,R.drawable.spotify))
        }
        val adapter = ArtistTopSongsAdapter(data,this)
        songRecyclerView?.adapter = adapter


        artistRecyclerView?.layoutManager = GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false)
        val data2 = ArrayList<TopArtistPojo>()
        for (i in 1..10) {
            data2.add(TopArtistPojo("A${i}","Artist $i","R.drawable.spotify"))
        }
        val adapter2 = SimilarArtistAdapter(data2,this)
        artistRecyclerView?.adapter = adapter2


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
        songRecyclerView = findViewById(R.id.artist_topSongsRecyclerView)
        artistRecyclerView = findViewById(R.id.artist_similarArtistRecyclerView)
    }

    private fun loadArtist(){

    }
}