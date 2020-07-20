package com.sandytech.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Placeholder

class ImageSwap : AppCompatActivity() {

     private lateinit var placeImage : Placeholder //declaration
     private lateinit var swapLayout : ConstraintLayout //declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_swap)

        // this is for title
        supportActionBar?.title = "Image Swap"

        //this is for back button on action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        placeImage = findViewById(R.id.placeImage) //initialization
        swapLayout = findViewById(R.id.swapLayout) //initialization
    }

    fun performSwap (v: View) {
        TransitionManager.beginDelayedTransition(swapLayout)
        placeImage.setContentId(v.id)
    }
}