package com.gh0osty.spoutifystats.Adapters

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.Screens.ArtistActivity
import com.gh0osty.spoutifystats.Screens.SongActivity
import com.gh0osty.spoutifystats.ViewModels.TopArtistViewModel

class Artist_SimilarArtistAdapter(private val list: List<TopArtistViewModel>, val context: Context?) :
    RecyclerView.Adapter<Artist_SimilarArtistAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.artistSimilarArtist_image)
        val title: TextView = itemView.findViewById(R.id.artistSimilarArtist_title)
        val cardView: CardView = itemView.findViewById(R.id.artistSimilarArtist_Card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.artist_similar_artist_card, parent, false)
        view.layoutParams = ViewGroup.LayoutParams((Resources.getSystem().displayMetrics.widthPixels*0.4).toInt(),ViewGroup.LayoutParams.MATCH_PARENT)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.title.text = item.text
        holder.cardView.setOnClickListener{
            val intent = Intent(it.context, ArtistActivity::class.java)
            val bundle = Bundle()
            bundle.putString("aId",item.id)
            bundle.putString("aImage",item.image.toString())
            bundle.putString("aName",item.text)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}