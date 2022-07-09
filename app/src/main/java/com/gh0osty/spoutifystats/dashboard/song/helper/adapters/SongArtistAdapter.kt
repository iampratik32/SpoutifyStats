package com.gh0osty.spoutifystats.dashboard.song.helper.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.dashboard.artist.ArtistActivity
import com.gh0osty.spoutifystats.dashboard.song.helper.pojos.SongArtistPojo

class SongArtistAdapter(private val list: List<SongArtistPojo>, val context: Context?) :
    RecyclerView.Adapter<SongArtistAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.songArtist_Image)
        val cardView: CardView = itemView.findViewById(R.id.songArtist_Card)
        val artist: TextView = itemView.findViewById(R.id.songArtist_Name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_artist_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.artist.text = item.name
        holder.cardView.setOnClickListener{
            val intent = Intent(it.context, ArtistActivity::class.java)
            val bundle = Bundle()
            bundle.putString("aId",item.id)
            bundle.putString("aImage",item.image.toString())
            bundle.putString("aName",item.name)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}