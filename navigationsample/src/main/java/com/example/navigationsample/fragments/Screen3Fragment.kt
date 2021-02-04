package com.example.navigationsample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.navigationsample.R
import kotlinx.android.synthetic.main.fragment_screen1.*

class Screen3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_screen3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            //Navigation.findNavController(it).navigate(R.id.action_screen3_to_screen1Fragment)
            val navOption = NavOptions.Builder().setPopUpTo(R.id.screen1Fragment, true).build()
            findNavController().navigate(
                R.id.action_screnn3Fragment_to_screen1Fragment,
                null,
                navOption
            )
        }
    }
}