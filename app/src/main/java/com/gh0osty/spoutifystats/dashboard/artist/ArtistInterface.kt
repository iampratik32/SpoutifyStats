package com.gh0osty.spoutifystats.dashboard.artist

import android.os.Bundle
import com.gh0osty.spoutifystats.dashboard.artist.helper.pojos.ArtistPojo
import com.gh0osty.spoutifystats.dashboard.artist.helper.pojos.ArtistTopSongPojo
import com.gh0osty.spoutifystats.utilities.interfaces.ControllerInit
import com.gh0osty.spoutifystats.utilities.interfaces.ViewInit

interface ArtistInterface {
    interface View : ViewInit.Apis {
        fun getBundle(): Bundle
        fun setSimilarArtistList(artists: List<ArtistPojo>)
        fun setTopSongList(songs: List<ArtistTopSongPojo>)
        fun setInitialInfo(name: String, image: String, id: String)
    }

    interface Controller : ControllerInit {
        fun getArtistInfo(id: String)
        fun getSimilarArtist(id: String)
        fun getTopSongs(id: String)
    }
}