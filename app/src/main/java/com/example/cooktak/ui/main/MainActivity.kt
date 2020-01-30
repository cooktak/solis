package com.example.cooktak.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.R
import com.example.cooktak.adapter.ViewPagerAdapter
import com.example.cooktak.util.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private lateinit var back: BackPressCloseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setSupportActionBar(toolbar)

        val actionBar: ActionBar = this.supportActionBar!!
        actionBar.setDisplayShowCustomEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)

        btn_settings.setOnClickListener {
            toast("Settings")
        }

        back = BackPressCloseHandler(this)

        view_pager_main.adapter = ViewPagerAdapter(supportFragmentManager)

        tab_layout_main.setupWithViewPager(view_pager_main)

        tab_layout_main.getTabAt(0)?.setIcon(R.drawable.barcode_size_small)
        tab_layout_main.getTabAt(1)?.setIcon(R.drawable.icon_search_main)
        tab_layout_main.getTabAt(2)?.setIcon(R.drawable.community)
    }

    override fun onBackPressed() {
        back.onBackPressed()
    }
}
