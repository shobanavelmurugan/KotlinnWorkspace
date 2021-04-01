package com.shobanasha.viewbindingexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.shobanasha.viewbindingexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button.setOnClickListener {
            Toast.makeText(this@MainActivity, "Clicked me", Toast.LENGTH_SHORT).show()
            changeConstraintProgrammatically()
        }
    }

    private fun changeConstraintProgrammatically() {

        val constraintSet = ConstraintSet()
        //Copy all the previous constraints present in the constraint layout.
        constraintSet.clone(binding.layoutParent)

        //constraintSet.connect(binding.button.id, ConstraintSet.TOP, binding.layoutParent.id, ConstraintSet.TOP)
        constraintSet.connect(
            binding.button.id,
            ConstraintSet.BOTTOM,
            binding.layoutParent.id,
            ConstraintSet.BOTTOM
        )
        ////constraintSet.connect(binding.button.id, ConstraintSet.LEFT, binding.layoutParent.id, ConstraintSet.LEFT)
        //constraintSet.connect(binding.button.id, ConstraintSet.RIGHT, binding.layoutParent.id, ConstraintSet.RIGHT)
        constraintSet.applyTo(binding.layoutParent)
/*
        val parentLayout = findViewById<View>(R.id.mainConstraint) as ConstraintLayout
        val set = ConstraintSet()

        val childView = ImageView(this)
        // set view id, else getId() returns -1
        childView.id = View.generateViewId()
        parentLayout.addView(childView, 0)

        set.clone(parentLayout)
        // connect start and end point of views, in this case top of child to top of parent.
        // connect start and end point of views, in this case top of child to top of parent.
        set.connect(childView.id, ConstraintSet.TOP, parentLayout.id, ConstraintSet.TOP, 60)
        // ... similarly add other constraints
        // ... similarly add other constraints
        set.applyTo(parentLayout)*/
    }
}