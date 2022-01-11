package com.gh0osty.spoutifystats.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.Utilities.Helper
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.gh0osty.spoutifystats.Controllers.AuthController
import org.json.JSONObject
import java.net.URL


class LoginActivity : AppCompatActivity() {
    var loginButton: CircularProgressButton ?= null
    var authController=AuthController()

    var CLIENT_ID = Helper.getClientId()
    var CLIENT_SECRET = Helper.getClientSecret()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val url = intent.data
        if(url!==null){
            if(url.query.toString()=="error=access_denied"){
                Toast.makeText(this,"Access Denied. Please Try Again.",Toast.LENGTH_LONG).show()
            }
            else if(url.getQueryParameter("code")!=null){
                val token = url.getQueryParameter("code").toString()
                authController.getAuthToken(this,token, object :
                    AuthController.AuthResponseListener {

                    override fun onError(message: String?) {
                        Log.d("MESSAGE", message!!)
                    }

                    override fun onResponse(response: Any?) {
                        val obj = JSONObject(response as String)
                        Log.d("TOKEN2",obj.getString("access_token"))

                        // TODO:: STORE THIS TOKEN IN DB AND DON'T LOG IN EVERYTIME
                    }
                })

            }
        }

        loginButton = findViewById(R.id.loginButton)
        loginButton?.setOnClickListener {
            showLogin()
        }
    }

    private fun showLogin() {
        loginButton?.startAnimation()


        // TODO:: SHOW LOGIN PAGE
        //loginButton.revertAnimation { loginButton.text = getString(R.string.loginButtonProgress) }

        val url = "https://accounts.spotify.com/authorize?redirect_uri=spoutifystats://oauthredirect/" +
                "&client_id=2a8b05a3d3c545ac9940e534bffa43cf&response_type=code" +
                "&scope=ugc-image-upload%20user-read-playback-state%20user-modify-playback-state%20user-read-currently-playing%20user-read-email%20user-read-private%20playlist-read-collaborative%20playlist-modify-public%20playlist-read-private%20playlist-modify-private%20user-library-modify%20user-library-read%20user-top-read%20user-read-playback-position%20user-read-recently-played%20user-follow-read%20user-follow-modify"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        // TODO:: DEEP LINKING
    }


}