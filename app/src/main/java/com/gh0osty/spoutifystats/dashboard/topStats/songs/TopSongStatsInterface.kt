package com.gh0osty.spoutifystats.dashboard.topStats.songs

import android.content.Context
import com.gh0osty.spoutifystats.utilities.interfaces.ControllerInit
import com.gh0osty.spoutifystats.utilities.interfaces.ViewInit
import com.gh0osty.spoutifystats.dashboard.topStats.songs.helper.TopSongPojo

interface TopSongStatsInterface {
    interface View : ViewInit.Apis {
        fun setSongs(songs: List<TopSongPojo>)
        fun setTitle(title: String)
        fun createSpinnerAdapter()
    }

    interface Controller : ControllerInit {
        fun getTopSongs(context: Context, term: String)
    }
}