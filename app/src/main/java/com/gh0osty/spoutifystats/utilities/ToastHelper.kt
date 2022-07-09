package com.gh0osty.spoutifystats.utilities

import android.content.Context
import android.widget.Toast

fun createToast(context: Context, message: String, short: Boolean) {
    Toast.makeText(context, message, if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
}