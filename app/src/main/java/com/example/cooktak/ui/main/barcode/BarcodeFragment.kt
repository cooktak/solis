package com.example.cooktak.ui.main.barcode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cooktak.R
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager
import kotlinx.android.synthetic.main.fragment_barcode.view.*


class BarcodeFragment : Fragment() {
    lateinit var captureManager: CaptureManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_barcode, container, false)

        v.barcode_layout.setOnClickListener {
            val integrator = IntentIntegrator.forSupportFragment(this)
            integrator.captureActivity = CustomScannerActivity::class.java
            integrator.setCameraId(0)
            integrator.setBeepEnabled(false)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }

        return v
    }

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
