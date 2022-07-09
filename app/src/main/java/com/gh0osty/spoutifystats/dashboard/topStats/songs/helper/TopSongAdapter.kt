package com.gh0osty.spoutifystats.dashboard.topStats.songs.helper

import android.annotation.SuppressLint
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
import com.bumptech.glide.Glide
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.dashboard.song.SongActivity

class TopSongAdapter(private val list: List<TopSongPojo>, val context: Context?) :
    RecyclerView.Adapter<TopSongAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.topSong_Image)
        val songName: TextView = itemView.findViewById(R.id.topSong_Name)
        val cardView: CardView = itemView.findViewById(R.id.topSong_Card)
        val index: TextView = itemView.findViewById(R.id.topSong_Index)
        val artist: TextView = itemView.findViewById(R.id.topSong_Artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_card, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        Glide.with(context!!).load(item.image).into(holder.imageView)
        holder.songName.text = item.text
        holder.index.text = "# ${position+1}"
        holder.artist.text = item.artist
        holder.cardView.setOnClickListener {
            val intent = Intent(it.context, SongActivity::class.java)
            val bundle = Bundle()
            bundle.putString("sId", item.id)
            bundle.putString("sImage", item.image)
            bundle.putString("sName", item.text)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}