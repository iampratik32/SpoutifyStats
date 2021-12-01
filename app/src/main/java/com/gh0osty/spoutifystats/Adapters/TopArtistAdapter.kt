package com.gh0osty.spoutifystats.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.ViewModels.TopArtistViewModel

class TopArtistAdapter(private val list: List<TopArtistViewModel>) :
    RecyclerView.Adapter<TopArtistAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.topArtist_Image)
        val textView: TextView = itemView.findViewById(R.id.topArtist_Name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.artist_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = list[position]

        holder.imageView.setImageResource(ItemsViewModel.image)

        holder.textView.text = ItemsViewModel.text
    }

    override fun getItemCount(): Int {
        return list.size
    }

}