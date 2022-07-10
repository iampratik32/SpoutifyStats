package com.gh0osty.spoutifystats.dashboard.artist

import android.content.Context
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.dashboard.artist.helper.pojos.ArtistPojo
import com.gh0osty.spoutifystats.dashboard.artist.helper.pojos.ArtistTopSongPojo
import com.gh0osty.spoutifystats.utilities.ApiHelper
import com.gh0osty.spoutifystats.utilities.Url
import org.json.JSONObject

class ArtistController(val view: ArtistInterface.View, val context: Context) :
    ArtistInterface.Controller {

    init {
        onCreate()
    }

    override fun getArtistInfo(id: String) {
        getTopSongs(id)
        getSimilarArtist(id)
    }

    override fun getSimilarArtist(id: String) {
        val url = "${Url.MAIN_URL}artists/${id}/related-artists"
        ApiHelper().call(context, url, object : ApiHelper.ApiListener {
            override fun onError(message: String?, code: Int?) {
                view.apiError(message.toString())
            }

            override fun onResponse(response: Any?, what: Boolean) {
                val obj = JSONObject(response as String)
                val items = obj.getJSONArray("artists")
                val data2 = ArrayList<ArtistPojo>()
                for (i in 0 until items.length()) {
                    data2.add(
                        ArtistPojo(
                            items.getJSONObject(i).getString("id"),
                            items.getJSONObject(i).getString("name"),
                            items.getJSONObject(i).getJSONArray("images").getJSONObject(1)
                                .getString("url"),
                            items.getJSONObject(i).getString("name")
                        )
                    )
                }
                view.setSimilarArtistList(data2)
            }
        })
    }

    override fun getTopSongs(id: String) {
//        val url = "${Url.MAIN_URL}artists/$id/top-tracks"
//        ApiHelper().call(context, url, object : ApiHelper.ApiListener {
//            override fun onError(message: String?, code: Int?) {
//                view.apiError("$code: ${message.toString()}")
//            }
//
//            override fun onResponse(response: Any?, what: Boolean) {
//                val obj = JSONObject(response as String)
//                val items = obj.getJSONArray("tracks")
//                val data2 = ArrayList<ArtistTopSongPojo>()
//                for (i in 0 until items.length()) {
//                    data2.add(
//                        ArtistTopSongPojo(
//                            "asd",
//                            "Song Name",
//                            1,
//                            "Test"
//                        )
//                    )
//                }
//                view.setTopSongList(data2)
//            }
//        })
    }

    override fun onCreate() {
        val bundle = view.getBundle()
        view.setInitialInfo(
            bundle.getString("aName", ""),
            bundle.getString("aImage", ""),
            bundle.getString("aId", "")
        )
        getArtistInfo(bundle.getString("aId", ""))
    }
}