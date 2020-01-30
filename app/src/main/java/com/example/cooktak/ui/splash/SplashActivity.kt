package com.example.cooktak.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.cooktak.R
import com.example.cooktak.ui.sign.SelectSignActivity

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)

        val intent = Intent(this, SelectSignActivity::class.java)
        startActivity(intent)
        finish()
    }
}