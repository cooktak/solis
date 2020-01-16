package com.example.cooktak.ui.Sign

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.R
import com.example.cooktak.ui.Main.MainActivity
import kotlinx.android.synthetic.main.activity_select_sign.*


class SelectSignActivity : AppCompatActivity() {

    var lastTimeBackPressed: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_sign)

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
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finish()
            return
        }
        lastTimeBackPressed = System.currentTimeMillis()
        Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
    }
}
