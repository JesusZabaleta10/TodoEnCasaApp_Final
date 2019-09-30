package com.example.todoencasaapp_final

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hiding tittle bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        // Marking this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        // 2 seconds splash time
        Handler().postDelayed({
            // Start LoginActivity
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finish this activity
            finish()
        },2000)
    }
}
