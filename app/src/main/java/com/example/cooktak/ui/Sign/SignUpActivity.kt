package com.example.cooktak.ui.Sign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import com.example.cooktak.R
import com.example.cooktak.connecter.Connector
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val datePick = findViewById<DatePicker>(R.id.date_birth_register)
        val cal = Calendar.getInstance()
        cal.time = Date()
        val calendar = listOf(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        datePick.init(calendar[0], calendar[1], calendar[2], null)


        btn_check_register.setOnClickListener {
            if (edt_email_register.text.isNotEmpty() && edt_pwd_register.text.isNotEmpty() && edt_conPwd_register.text.isNotEmpty()
                && edt_nick_register.text.isNotEmpty()
            ) {

                if (edt_pwd_register.text.toString() == edt_conPwd_register.text.toString()) {
                    val obj = JsonObject()
                    obj.addProperty("id", "admin")
                    obj.addProperty("email", edt_email_register.text.toString())
                    obj.addProperty("name", "Dart")
                    obj.addProperty("nickName", edt_nick_register.text.toString())
                    obj.addProperty(
                        "birth",
                        String.format("%04d-%02d-%02d", datePick.year, datePick.month + 1, datePick.dayOfMonth)
                    )
                    when (radio_group_register.checkedRadioButtonId) {
                        R.id.radio_btn_man -> obj.addProperty("sex", "m")
                        R.id.radio_btn_woman -> obj.addProperty("sex", "w")
                        R.id.radio_btn_unknown -> obj.addProperty("sex", "u")
                    }
                    obj.addProperty("password", edt_pwd_register.text.toString())

                    Connector.createApi().registerUser(obj).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            when (response.code()) {
                                200 -> {
                                    Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                                }
                                else -> {
                                    Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                                    Log.d("Connect Failure : ", response.body().toString())
                                }
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    })

                } else {
                    Toast.makeText(this@SignUpActivity, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this@SignUpActivity, "빈칸이 존재합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

/*
val dateStr = "2019-03-17"
val date = SimpleDateFormat("yyyy-MM-dd").parse(dateStr)
val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
sdf.timeZone = TimeZone.getTimeZone("CET")
val dateText = sdf.format(date)
println(dateText)

 */