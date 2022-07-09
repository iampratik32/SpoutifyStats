package com.gh0osty.spoutifystats.auth

import android.content.Context
import com.gh0osty.spoutifystats.utilities.ApiHelper
import com.gh0osty.spoutifystats.utilities.Url

class UserController {

    fun getProfile(context: Context, listener: ApiHelper.ApiListener) {
        val url = "${Url.MAIN_URL}me"
        ApiHelper().call(context, url, object : ApiHelper.ApiListener {
            override fun onError(message: String?, code: Int?) {
                listener.onError(message, code)
            }

            override fun onResponse(response: Any?, what: Boolean) {
                listener.onResponse(response, false)
            }
        })
    }
}