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


class LoginActivity : AppCompatActivity() {
    var loginButton: CircularProgressButton ?= null

    var CLIENT_ID = Helper.getClientId()
    var CLIENT_SECRET = Helper.getClientSecret()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.loginButton)
        loginButton?.setOnClickListener {
            showLogin()
        }
    }

    private fun showLogin() {
        loginButton?.startAnimation()


        // TODO:: SHOW LOGIN PAGE
        //loginButton.revertAnimation { loginButton.text = getString(R.string.loginButtonProgress) }

        val url = "https://accounts.spotify.com/authorize?redirect_uri=com.gh0osty.spoutifystats://oauthredirect" +
                "&client_id=2a8b05a3d3c545ac9940e534bffa43cf&response_type=code" +
                "&scope=ugc-image-upload%20user-read-playback-state%20user-modify-playback-state%20user-read-currently-playing%20user-read-email%20user-read-private%20playlist-read-collaborative%20playlist-modify-public%20playlist-read-private%20playlist-modify-private%20user-library-modify%20user-library-read%20user-top-read%20user-read-playback-position%20user-read-recently-played%20user-follow-read%20user-follow-modify"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        // TODO:: DEEP LINKING
    }


}