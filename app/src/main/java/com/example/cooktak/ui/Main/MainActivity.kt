package com.example.cooktak.ui.Main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.Adapter.ViewPagerAdapter
import com.example.cooktak.R
import com.example.cooktak.util.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var back: BackPressCloseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
