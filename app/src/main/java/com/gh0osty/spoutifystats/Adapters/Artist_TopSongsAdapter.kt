package com.gh0osty.spoutifystats.Adapters

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.Screens.SongActivity
import com.gh0osty.spoutifystats.ViewModels.Artist_TopSongsViewModel

class Artist_TopSongsAdapter(private val list: List<Artist_TopSongsViewModel>, val context: Context?) :
    RecyclerView.Adapter<Artist_TopSongsAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.artistTopSong_image)
        val index: TextView = itemView.findViewById(R.id.artistTopSong_index)
        val title: TextView = itemView.findViewById(R.id.artistTopSong_title)
        val cardView: CardView = itemView.findViewById(R.id.artistTopSong_Card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.artist_song_card, parent, false)
//        Log.d("WIDTH", .toString())
        view.layoutParams = ViewGroup.LayoutParams((Resources.getSystem().displayMetrics.widthPixels*0.4).toInt(),ViewGroup.LayoutParams.MATCH_PARENT)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.title.text = item.title
        holder.index.text = "${item.index}."
        holder.cardView.setOnClickListener{
            val intent = Intent(it.context, SongActivity::class.java)
            val bundle = Bundle()
            bundle.putString("sId",item.id)
            bundle.putString("sImage",item.image.toString())
            bundle.putString("sTitle",item.title)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

}

