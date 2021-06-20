package com.ervalsa.tmdb.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.ervalsa.tmdb.R
import com.ervalsa.tmdb.databinding.ActivitySplashBinding
import com.ervalsa.tmdb.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        const val timer = 2000
    }

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, timer.toLong())
    }
}