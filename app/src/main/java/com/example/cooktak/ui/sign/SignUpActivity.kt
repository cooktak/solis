package com.example.cooktak.ui.sign

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cooktak.R
import com.example.cooktak.connecter.Connector
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val datePick = findViewById<DatePicker>(R.id.date_birth_register)

        val cal = Calendar.getInstance()
        cal.time = Date()

        val calendar =
            listOf(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        datePick.init(calendar[0], calendar[1], calendar[2], null)


        btn_check_register.setOnClickListener {
            val gender = when (radio_group_register.checkedRadioButtonId) {
                R.id.radio_btn_man -> "m"
                R.id.radio_btn_woman -> "w"
                R.id.radio_btn_unknown -> "u"
                else -> "u"
            }

            val birthday = formatDate(
                String.format(
                    "%04d-%02d-%02d",
                    datePick.year,
                    datePick.month + 1,
                    datePick.dayOfMonth
                )
            )

            val obj = JsonObject()
            obj.addProperty("userName", edt_email_register.text.toString())
            obj.addProperty("password", edt_pwd_register.text.toString())
            obj.addProperty("passwordCon", edt_conPwd_register.text.toString())
            obj.addProperty("nickName", edt_nick_register.text.toString())
            obj.addProperty("birthday", birthday)
            obj.addProperty("gender", gender)

            when (checkSignUp(obj)) {
                200 -> register(obj)
                201 -> toast("공백이 존재합니다.")
                202 -> toast("비밀번호가 일치하지 않습니다.")
                203 -> toast("비밀번호에 특수문자 또는 공백이 존재합니다.")
                204 -> toast("비밀번호에 같은 문자 3개 이상은 사용할 수 없습니다.")
                205 -> toast("비밀번호의 길이가 맞지 않습니다.")
                206 -> toast("숫자와 영어를 혼합하여 사용하지 않았습니다.")
                207 -> toast("이메일 형식이 맞지 않습니다.")
            }


        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        val setDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val formatPattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        formatPattern.timeZone = TimeZone.getTimeZone("Asia/Seoul")

        return formatPattern.format(setDate)
    }

    private fun checkSignUp(obj: JsonObject): Int {
        val email = obj["userName"].asString
        val password = obj["password"].asString
        val passwordCon = obj["passwordCon"].asString

        val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        val pwPattern = "(.)\\1\\1"
        val pwdPattern = "^(?=.*\\d)(?=.*[a-z]).{8,20}$"
        val pattern = Regex("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")

        if (email.isEmpty() || password.isEmpty() || passwordCon.isEmpty())
            return 201      // 공백
        else if (password != passwordCon)
            return 202      // 비밀번호 불일치
        else if (!password.matches(pattern))
            return 203      // 특수문자 또는 공백 존재
        else if (Pattern.compile(pwPattern).matcher(password).find())
            return 204      // 같은 문자 3개 이상 사용불가
        else if (password.length < 8 || password.length > 20)
            return 205      // 최소 길이 이하 또는 최대 길이 이상
        else if (!Pattern.compile(pwdPattern).matcher(password).matches())
            return 206      // 숫자와 영어 혼합 안함
        else if (!Pattern.compile(emailPattern).matcher(email).matches())
            return 207      // 이메일 형식 위반

        return 200      // 통과
    }

    private fun register(obj: JsonObject) {
        Connector.createApi().registerUser(obj).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when (response.code()) {
                    200 -> {
                        Toast.makeText(
                            this@SignUpActivity,
                            "회원가입 성공",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent =
                            Intent(this@SignUpActivity, SignInActivity::class.java)
                        intent.putExtra("id", obj["email"].toString())
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                    else -> {
                        Toast.makeText(
                            this@SignUpActivity,
                            "회원가입 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent =
                            Intent(this@SignUpActivity, SignInActivity::class.java)
                        intent.putExtra("email", obj["userName"].asString)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                        Log.d("Connect Failure : ", response.body().toString())
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }
}