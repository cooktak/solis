package com.example.cooktak.ui.main.community

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.cooktak.R
import kotlinx.android.synthetic.main.activity_create.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CreateActivity : AppCompatActivity() {
    private var tempImage: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btn_upload.setOnClickListener {
            selectType()
        }
    }

    private fun selectType() {
        val builder = CreateDialog(this)
        builder.setCancelable(true)
        builder.window?.setGravity(Gravity.CENTER)
        builder.setListener(object : CreateDialog.CreateDialogListener {
            override fun applyType(type: String) {
                when (type) {
                    "camera" -> uploadFromCamera()
                    "gallery" -> uploadFromGallery()
                }
            }
        })
        builder.show()
    }

    private fun uploadFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            this.tempImage = createImageFile()
        } catch (e: IOException) {
            e.stackTrace
            finish()
        }

        if (tempImage != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                val uri = FileProvider.getUriForFile(
                    this,
                    "{package name}.provider", tempImage!!
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(intent, 2)
            } else {
                val uri = Uri.fromFile(tempImage)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(intent, 2)
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("HHmmss").format(Date())
        val imageFileName = "test-$timeStamp"
        val storageDir = File(Environment.getExternalStorageState() + "/test/")

        if (!storageDir.exists())
            storageDir.mkdir()

        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    private fun uploadFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        this@CreateActivity.startActivityForResult(intent, 1)
    }

    private fun setImage() {
        val option = BitmapFactory.Options()
        val originalBm = BitmapFactory.decodeFile(tempImage?.absolutePath, option)
        img_test.setImageBitmap(originalBm)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this@CreateActivity, "취소 되었습니다.", Toast.LENGTH_SHORT).show()

            if (tempImage != null) {
                if (tempImage!!.exists()) {
                    if (tempImage!!.delete()) {
                        Log.e("tempImageDelete", "${tempImage!!.absolutePath} 제거 완료")
                        this.tempImage = null
                    }
                }
            }

            return
        }

        if (requestCode == 1) {
            val uri: Uri? = data?.data
            var cursor: Cursor? = null

            try {
                val proj =
                    arrayOf(MediaStore.Images.Media.DATA)

                assert(uri != null)
                cursor = contentResolver.query(uri!!, proj, null, null, null)

                assert(cursor != null)
                val column_index = cursor!!.getColumnIndex(MediaStore.Images.Media.DATA)

                cursor.moveToFirst()

                tempImage = File(cursor.getString(column_index))

            } catch (e: Exception) {
                e.stackTrace
            } finally {
                if (cursor != null)
                    cursor.close()
            }
            setImage()
        } else if (requestCode == 2) {
            setImage()
        }
    }
}
