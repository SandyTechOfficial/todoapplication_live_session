package com.sandytech.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class RollTheDiceActivity : AppCompatActivity() {

    private lateinit var txtRollNumber : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll_the_dice)

        // this is for title
        supportActionBar?.title = "Roll The Dice"

        //this is for back button on action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtRollNumber = findViewById(R.id.txtRollNumber)

        val btnRoll = findViewById<Button>(R.id.btnRoll)
        btnRoll.setOnClickListener{
            //val random = Random().nextInt()
            //val random = Random().nextInt(6)
            val random = Random().nextInt(6) + 1

            txtRollNumber.text = random.toString()
        }
    }

}