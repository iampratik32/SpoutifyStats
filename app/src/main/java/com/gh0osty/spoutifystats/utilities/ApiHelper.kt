package com.gh0osty.spoutifystats.utilities

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.gh0osty.spoutifystats.auth.MainController
import com.gh0osty.spoutifystats.auth.pojos.AuthPojo
import io.paperdb.Paper
import org.json.JSONObject

class ApiHelper {
    companion object {
        val mainController = MainController()
    }

    interface ApiListener {
        fun onError(message: String?, code: Int?)
        fun onResponse(response: Any?, what: Boolean)
    }

    fun call(context: Context, url: String, listener: ApiListener) {
        val auth = Paper.book().read<AuthPojo>("Auth")
        Log.d("Test123",url)

        val request = object : StringRequest(
            Method.GET, url,
            Response.Listener { response -> listener.onResponse(response, false) },
            Response.ErrorListener { error ->
                Log.d("TEST123",error.networkResponse.statusCode.toString())
                if (error.networkResponse.statusCode == 401 && auth !== null) {
                    mainController.getRefreshToken(
                        context,
                        auth.refreshToken,
                        object : MainController.RefreshListener {
                            override fun onError(message: String?, code: Int?) {
                                listener.onError(message, code)
                            }

                            override fun onResponse(response: Any?) {
                                val obj = JSONObject(response as String)
                                val token = obj.getString("access_token")
                                val newAuth = AuthPojo(token, auth.refreshToken)
                                Paper.book().write("Auth", newAuth)
                                listener.onResponse(response, true)
                            }

                        })
                } else {
                    listener.onError(error.toString(), error.networkResponse.statusCode)
                }
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Content-Type"] = "application/json"
                headers["Accept"] = "application/json"
                headers["Authorization"] = "Bearer " + auth?.token

                return headers
            }
        }
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }
}