package com.example.cooktak.ui.main.community

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.example.cooktak.R
import kotlinx.android.synthetic.main.create_dialog.*

class CreateDialog(context: Context) : Dialog(context) {

    private lateinit var listener: CreateDialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lp = WindowManager.LayoutParams()
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lp.dimAmount = 0.8f
        window!!.attributes = lp

        setContentView(R.layout.create_dialog)

        btn_camera.setOnClickListener {
            listener.applyType("camera")
            dismiss()
        }

        btn_gallery.setOnClickListener {
            listener.applyType("gallery")
            dismiss()
        }
    }

    interface CreateDialogListener {
        fun applyType(type: String)
    }

    fun setListener(listener: CreateDialogListener) {
        this.listener = listener
    }
}