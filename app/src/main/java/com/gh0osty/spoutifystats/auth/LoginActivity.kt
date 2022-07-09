package com.gh0osty.spoutifystats.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.gh0osty.spoutifystats.R
import com.gh0osty.spoutifystats.utilities.Helper
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.gh0osty.spoutifystats.auth.pojos.AuthPojo
import com.gh0osty.spoutifystats.auth.pojos.UserPojo
import com.gh0osty.spoutifystats.dashboard.MainActivity
import com.gh0osty.spoutifystats.utilities.ApiHelper
import io.paperdb.Paper
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    var loginButton: CircularProgressButton? = null
    var loading: LottieAnimationView? = null
    var authController = AuthController()
    var userController = UserController()

    var CLIENT_ID = Helper.getClientId()
    var CLIENT_SECRET = Helper.getClientSecret()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Paper.init(applicationContext)
        loginButton = findViewById(R.id.loginButton)
        loading = findViewById(R.id.loadingAnimation)
        //TODO:: CHANGE THE LOADING GIF SPEED TO QUICK CAUSE ITS SLOW

        if (Paper.book().read<AuthPojo>("Auth") !== null) {
            getUser()
        } else {
            loginButton?.visibility = View.VISIBLE
            loading?.visibility = View.GONE
        }

        val url = intent.data
        if (url !== null) {
            if (url.query.toString() == "error=access_denied") {
                Toast.makeText(this, "Access Denied. Please Try Again.", Toast.LENGTH_LONG).show()
            } else if (url.getQueryParameter("code") != null) {
                val token = url.getQueryParameter("code").toString()
                authController.getAuthToken(this, token, object :
                    AuthController.AuthResponseListener {

                    override fun onError(message: String?) {
                        Log.d("MESSAGE", message!!)
                    }

                    override fun onResponse(response: Any?) {
                        val obj = JSONObject(response as String)
                        Paper.book().write(
                            "Auth",
                            AuthPojo(obj.getString("access_token"), obj.getString("refresh_token"))
                        )
                        getUser()
                    }
                })

            }
        }

        loginButton?.setOnClickListener {
            showLogin()
        }
    }

    private fun getUser() {
        userController.getProfile(this, object : ApiHelper.ApiListener {
            override fun onError(message: String?, code: Int?) {
                message?.let { Log.d("ERROR HERE", message) }
            }

            override fun onResponse(response: Any?, what: Boolean) {
                if (what) {
                    getUser()
                } else {
                    //TODO:: STORE NEW USER INFO IN THE DATABASE & MOVE FORWARD
                    val obj = JSONObject(response as String)
                    val user = UserPojo(
                        obj.getString("display_name"),
                        obj.getString("uri"),
                        obj.getString("product"),
                        obj.getJSONArray("images").getJSONObject(0).getString("url"),
                        obj.getJSONObject("followers").getInt("total")
                    )
                    Paper.book().write("User", user)

                    openMain()
                }

            }
        })
    }

    private fun openMain() {
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }

    private fun showLogin() {
        loginButton?.startAnimation()


        val url =
            "https://accounts.spotify.com/authorize?redirect_uri=spoutifystats://oauthredirect/" +
                    "&client_id=2a8b05a3d3c545ac9940e534bffa43cf&response_type=code" +
                    "&scope=ugc-image-upload%20user-read-playback-state%20user-modify-playback-state%20user-read-currently-playing%20user-read-email%20user-read-private%20playlist-read-collaborative%20playlist-modify-public%20playlist-read-private%20playlist-modify-private%20user-library-modify%20user-library-read%20user-top-read%20user-read-playback-position%20user-read-recently-played%20user-follow-read%20user-follow-modify"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        loginButton?.revertAnimation()
    }


}