package com.gh0osty.spoutifystats.dashboard.topStats.artists

import android.content.Context
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.utilities.ApiHelper
import com.gh0osty.spoutifystats.utilities.Url
import com.gh0osty.spoutifystats.dashboard.topStats.helper.TopArtistPojo
import org.json.JSONObject

class TopArtistStatsController(val view: TopArtistStatsInterface.View, val context: Context) :
    TopArtistStatsInterface.Controller {

    init {
        onCreate()
    }

    override fun getTopArtists(context: Context, term: String) {
        val url = "${Url.MAIN_URL}me/top/artists?limit=50&offset=0&time_range=$term"
        ApiHelper().call(context, url, object : ApiHelper.ApiListener {
            override fun onError(message: String?, code: Int?) {
                view.apiError(message.toString())
            }

            override fun onResponse(response: Any?, what: Boolean) {
                val obj = JSONObject(response as String)
                val items = obj.getJSONArray("items")
                val data = ArrayList<TopArtistPojo>()
                for (i in 0 until items.length()) {
                    data.add(
                        TopArtistPojo(
                            items.getJSONObject(i).getString("id"),
                            items.getJSONObject(i).getString("name"),
                            items.getJSONObject(i).getJSONArray("images").getJSONObject(1)
                                .getString("url")
                        )
                    )
                }
                view.setArtists(data)
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
        getTopArtists(context, "short_term")
        view.createSpinnerAdapter()
    }


}