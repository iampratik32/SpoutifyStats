package com.gh0osty.spoutifystats.Controllers

import android.content.Context

import com.gh0osty.spoutifystats.Utilities.VolleySingleton

import com.android.volley.*

import com.android.volley.toolbox.StringRequest





class AuthController {

    interface AuthResponseListener {
        fun onError(message: String?)
        fun onResponse(response: Any?)
    }

    fun getAuthToken(context: Context, code: String, listener: AuthResponseListener) {
        val url = "https://accounts.spotify.com/api/token"
        val params = HashMap<String,String>()
        params["grant_type"] = "authorization_code"
        params["redirect_uri"] = "spoutifystats://oauthredirect/"
        params["client_id"] = "2a8b05a3d3c545ac9940e534bffa43cf"
        params["client_secret"] = "cf0bb7f2b3254443b9112850f66da718"
        params["code"] = code

        val request = object : StringRequest(Method.POST, url,
            Response.Listener { response -> listener.onResponse(response) },
            Response.ErrorListener { error -> listener.onError(error.toString()) }) {
            override fun getParams(): Map<String, String> {
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Content-Type"] = "application/x-www-form-urlencoded" //put your token here

                return headers
            }

        }


        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )




        VolleySingleton.getInstance(context).addToRequestQueue(request)

    }

}