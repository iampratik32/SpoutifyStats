package com.gh0osty.spoutifystats.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gh0osty.spoutifystats.R


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val mainView : View = inflater.inflate(R.layout.fragment_settings, container, false)

        return mainView
    }

}