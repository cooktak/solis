package com.example.cooktak.ui.main.community

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.R

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }
}
