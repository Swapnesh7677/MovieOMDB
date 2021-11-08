package com.swapnesh.movies.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.swapnesh.appmovie.MainActivity
import com.swapnesh.appmovie.databinding.ActivitySplashBinding
import com.triologic.jewel_cliq.repository.ListRepository


class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ListRepository.initializeDB(this@SplashActivity)
        Handler().postDelayed({
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}