package com.gh0osty.spoutifystats.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gh0osty.spoutifystats.Fragments.HomeFragment
import com.gh0osty.spoutifystats.Fragments.SettingsFragment
import com.gh0osty.spoutifystats.Fragments.StatsFragment
import com.gh0osty.spoutifystats.Fragments.TopStatsFragment
import com.gh0osty.spoutifystats.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)

        bottomNav.menu.getItem(0).isCheckable = true
        setFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.bottomMenu_home -> {
                    setFragment(HomeFragment())
                    true
                }
                R.id.bottomMenu_settings -> {
                    setFragment(SettingsFragment())
                    true
                }
                R.id.bottomMenu_stats -> {
                    setFragment(StatsFragment())
                    true
                }
                R.id.bottomMenu_topStats -> {
                    setFragment(TopStatsFragment())
                    true
                }

                else -> false
            }
        }

    }

    private fun setFragment(fr: Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.mainFrameLayout, fr)
        frag.commit()
    }

}