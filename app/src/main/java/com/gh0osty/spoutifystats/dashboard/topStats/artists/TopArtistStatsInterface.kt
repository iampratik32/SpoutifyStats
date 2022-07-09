package com.gh0osty.spoutifystats.dashboard.topStats.artists

import android.content.Context
import com.gh0osty.spoutifystats.utilities.interfaces.ControllerInit
import com.gh0osty.spoutifystats.utilities.interfaces.ViewInit
import com.gh0osty.spoutifystats.dashboard.topStats.helper.TopArtistPojo

interface TopArtistStatsInterface {
    interface View : ViewInit.Apis {
        fun setArtists(artists: List<TopArtistPojo>)
        fun setTitle(title: String)
        fun createSpinnerAdapter()
    }

    interface Controller : ControllerInit {
        fun getTopArtists(context: Context, term: String)
    }
}