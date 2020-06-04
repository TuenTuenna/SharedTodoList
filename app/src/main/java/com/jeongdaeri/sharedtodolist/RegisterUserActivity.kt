package com.jeongdaeri.sharedtodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {


    companion object {
        const val TAG: String = "로그"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        Log.d(TAG, "RegisterUserActivity - onCreate() called")


        // TODO:: 추가 작업

        // 회원가입 버튼이 클릭
        register_user_btn.setOnClickListener {


            if(!user_id_text.text.isEmpty() || user_name_text.text.isNotEmpty() || user_age_text.text.isNotEmpty()){

                val userId = user_id_text.text.toString()
                val userName = user_name_text.text.toString()
                val userAge = user_age_text.text.toString().toInt()

                // 입력 비우기
                user_id_text.setText("")
                user_name_text.setText("")
                user_age_text.setText("")

                // 신규 사용자
                val newUser = User(userId, userName, userAge)

                // 신규 사용자 데이터 저장
                SharedManager.storeUser(newUser)
            } else {
                Toast.makeText(this, "내용을 적어주세요", Toast.LENGTH_SHORT).show()
            }

        }


        // 저장된 사용자 가져오기
        get_registered_user_btn.setOnClickListener {

            val storedUser = SharedManager.getUser()

            // 입력부분에 보여주기
            user_id_text.setText(storedUser.id)
            user_name_text.setText(storedUser.name)
            user_age_text.setText(storedUser.age.toString())
        }

    }








}
