package com.gh0osty.spoutifystats.Controllers

import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.gh0osty.spoutifystats.Models.AuthModel
import com.gh0osty.spoutifystats.Utilities.VolleySingleton
import io.paperdb.Paper
import org.json.JSONObject

class UserController {

    val mainController= MainController()

    interface ProfileListener {
        fun onError(message: String?, code: Int?)
        fun onResponse(response: Any?,what:Boolean)
    }

    fun getProfile(context: Context, listener: ProfileListener) {
        var url = "https://api.spotify.com/v1/me"
        val auth = Paper.book().read<AuthModel>("Auth")

        val request = object : StringRequest(Method.GET, url,
            Response.Listener { response -> listener.onResponse(response,false) },
            Response.ErrorListener { error ->
                if(error.networkResponse.statusCode==401 && auth!==null){
                    mainController.getRefreshToken(context,auth.refreshToken,object : MainController.RefreshListener{

                        override fun onError(message: String?, code: Int?) {
                            listener.onError(message,code)
                        }

                        override fun onResponse(response: Any?) {
                            val obj = JSONObject(response as String)
                            val token = obj.getString("access_token")
                            val newAuth = AuthModel(token,auth.refreshToken)
                            Paper.book().write("Auth",newAuth)
                            listener.onResponse(response, true)
                        }

                    })
                }
                else{
                    listener.onError(error.toString(), error.networkResponse.statusCode)
                }
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Content-Type"] = "application/json"
                headers["Accept"] = "application/json"
                headers["Authorization"] = "Bearer "+ auth?.token

                return headers
            }
        }

        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }
}