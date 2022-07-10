package com.gh0osty.spoutifystats.utilities

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadImage(context: Context, view: ImageView, url: String) {
    Glide.with(context).load(url).centerCrop().into(view)
}