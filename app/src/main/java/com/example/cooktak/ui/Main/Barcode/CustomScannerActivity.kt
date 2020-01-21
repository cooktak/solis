package com.example.cooktak.ui.Main.Barcode

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.cooktak.R
import com.example.cooktak.util.BackPressCloseHandler
import com.journeyapps.barcodescanner.CaptureManager


class CustomScannerActivity : FragmentActivity() {

    private lateinit var capture: CaptureManager
    private var switchFlashlightButtonCheck: Boolean = true
    private lateinit var backPressCloseHandler: BackPressCloseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_barcode)

        switchFlashlightButtonCheck = true
        backPressCloseHandler = BackPressCloseHandler(this)
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }
}

