package com.gh0osty.spoutifystats.dashboard.topStats.songs

import android.content.Context
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.utilities.ApiHelper
import com.gh0osty.spoutifystats.utilities.Url
import com.gh0osty.spoutifystats.dashboard.topStats.songs.helper.TopSongPojo
import org.json.JSONObject

class TopSongsStatsController(val view: TopSongStatsInterface.View, val context: Context) :
    TopSongStatsInterface.Controller {

    init {
        onCreate()
    }

    override fun getTopSongs(context: Context, term: String) {
        val url = "${Url.MAIN_URL}me/top/tracks?limit=50&offset=0&time_range=$term"
        ApiHelper().call(context, url, object : ApiHelper.ApiListener {
            override fun onError(message: String?, code: Int?) {
                view.apiError(message.toString())
            }

            override fun onResponse(response: Any?, what: Boolean) {
                val obj = JSONObject(response as String)
                val items = obj.getJSONArray("items")
                val data = ArrayList<TopSongPojo>()
                for (i in 0 until items.length()) {
                    val first = items.getJSONObject(i)
                    data.add(
                        TopSongPojo(
                            first.getString("id"),
                            first.getJSONArray("artists").getJSONObject(0).getString("name"),
                            first.getString("name"),
                            first.getJSONObject("album").getJSONArray("images").getJSONObject(1)
                                .getString("url")
                        )
                    )
                }
                view.setSongs(data)
            }
        })
        when (term) {
            "long_term" -> {
                view.setTitle(context.getString(R.string.topArtists3))
            }
            "short_term" -> {
                view.setTitle(context.getString(R.string.topArtists1))
            }
            else -> {
                view.setTitle(context.getString(R.string.topArtists2))
            }
        }
    }

    override fun onCreate() {
        view.createSpinnerAdapter()
        getTopSongs(context, "short_term")
    }
}