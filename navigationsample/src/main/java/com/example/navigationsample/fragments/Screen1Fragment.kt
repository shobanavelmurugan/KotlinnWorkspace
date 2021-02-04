package com.example.navigationsample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.navigationsample.R
import kotlinx.android.synthetic.main.fragment_screen1.*

class Screen1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_screen1, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_screen1Fragment_to_screen2Fragment)
        }
        buttonActivity.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_screen1Fragment_to_screenOne)
        }
    }
}