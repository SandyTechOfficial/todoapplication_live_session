package com.sandytech.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // this line is for full screen
        // for notification bar
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen)


        //for actionbar
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                // how to open a new activity
                val intent = Intent(this, LoginRegisterActivity::class.java)
                startActivity(intent)

                // jaha pr ho, us activity may ko close
                finish()

            }, 2000
        )
    }
}