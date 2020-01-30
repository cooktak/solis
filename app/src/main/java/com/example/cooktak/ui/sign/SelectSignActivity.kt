package com.example.cooktak.ui.sign

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.R
import com.example.cooktak.ui.main.MainActivity
import com.example.cooktak.util.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_select_sign.*


class SelectSignActivity : AppCompatActivity() {

    private lateinit var back: BackPressCloseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_sign)

        back = BackPressCloseHandler(this)

        btn_ani_main1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anime_trans_splash))
        btn_login_login.startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.anime_alpha_logo)
        )

        tex_guest.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anime_alpha_logo))

        touch_layout.setOnTouchListener { _, _ ->
            startActivity(Intent(this@SelectSignActivity, MainActivity::class.java))
            finish()
            true
        }

        btn_login_login.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        back.onBackPressed()
    }
}
