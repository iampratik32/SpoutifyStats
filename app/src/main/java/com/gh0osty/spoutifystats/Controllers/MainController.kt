package com.gh0osty.spoutifystats.Controllers

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.gh0osty.spoutifystats.Models.AuthModel
import com.gh0osty.spoutifystats.Utilities.VolleySingleton
import io.paperdb.Paper

class MainController {
    interface RefreshListener {
        fun onError(message: String?, code: Int?)
        fun onResponse(response: Any?)
    }

    fun getRefreshToken(context:Context, token: String, listener: RefreshListener){
        val url = "https://accounts.spotify.com/api/token"
        val params = HashMap<String,String>()
        params["grant_type"] = "refresh_token"
        params["client_id"] = "2a8b05a3d3c545ac9940e534bffa43cf"
        params["client_secret"] = "cf0bb7f2b3254443b9112850f66da718"
        params["refresh_token"] = token

        val request = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                listener.onResponse(response)
            },
            Response.ErrorListener { error -> listener.onError(error.toString(),error.networkResponse.statusCode) }) {
            override fun getParams(): Map<String, String> {
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Content-Type"] = "application/x-www-form-urlencoded"

                return headers
            }

        }


        VolleySingleton.getInstance(context).addToRequestQueue(request)

    }
}