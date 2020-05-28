package com.example.cooktak.ui.sign

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.R
import com.example.cooktak.ui.main.main.MainActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btn_login_signin.setOnClickListener {
            val email = edt_email_login.text.toString().trim()
            val password = edt_pass_login.text.toString().trim()

            when (checkSignIn(email, password)) {
                200 -> {
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
                201 -> toast("공백이 존재합니다.")
                207 -> toast("이메일 형식이 맞지 않습니다.")
            }
        }
        btn_register_signin.setOnClickListener {
            startActivityForResult(Intent(this@SignInActivity, SignUpActivity::class.java), 20)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 20) {
            edt_email_login.setText(data!!.getStringExtra("email"))
        }
    }

    private fun checkSignIn(email: String, password: String): Int {
        val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"

        if (email.isEmpty() || password.isEmpty())
            return 201      // 공백
        else if (!Pattern.compile(emailPattern).matcher(email).matches())
            return 207      // 이메일 형식 위반

        // Test 용
        if (email == "admin@admin.com" && password == "admin")
            return 200

        return 200
    }
}
