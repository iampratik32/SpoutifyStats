package com.gh0osty.spoutifystats.dashboard.topStats.artists.helper

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
import com.gh0osty.spoutifystats.dashboard.artist.ArtistActivity
import com.gh0osty.spoutifystats.dashboard.topStats.helper.TopArtistPojo

class TopArtistAdapter(private val list: List<TopArtistPojo>, val context: Context?) :
    RecyclerView.Adapter<TopArtistAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.topArtist_Image)
        val textView: TextView = itemView.findViewById(R.id.topArtist_Name)
        val cardView: CardView = itemView.findViewById(R.id.topArtist_Card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.artist_card, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.textView.text = (position+1).toString()+". "+item.text
        holder.cardView.setOnClickListener {
            val intent = Intent(it.context, ArtistActivity::class.java)
            val bundle = Bundle()
            bundle.putString("aId", item.id)
            bundle.putString("aImage", item.image)
            bundle.putString("aName", item.text)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }
        Glide.with(context!!).load(item.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}