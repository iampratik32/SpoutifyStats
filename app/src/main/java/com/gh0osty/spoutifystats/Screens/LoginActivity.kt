package com.gh0osty.spoutifystats.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.gh0osty.spoutifystats.R

class LoginActivity : AppCompatActivity() {
    var loginButton: CircularProgressButton ?= null
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

    }
}