package com.example.cooktak.ui.Main.Barcode

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager


class BarcodeFragment : Fragment() {
    lateinit var captureManager: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        /* val integrator : IntentIntegrator = IntentIntegrator(this.activity)
         integrator.captureActivity = CustomScannerActivity::class.java
         integrator.initiateScan()*/
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("onActivityResult", "onResult : .")
        if (resultCode == Activity.RESULT_OK) {
            val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            val re = scanResult.contents
            Log.d("onActivityResult", "onActivityResult: .$re")
            Toast.makeText(this.activity, re, Toast.LENGTH_LONG).show()
        }
    }
}
