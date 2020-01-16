package com.example.cooktak.ui.Sign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.R
import com.example.cooktak.ui.Main.MainActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btn_login_signin.setOnClickListener {
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            finish()
        }
        btn_register_signin.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }
}
