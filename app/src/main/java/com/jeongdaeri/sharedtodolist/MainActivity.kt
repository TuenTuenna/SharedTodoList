package com.jeongdaeri.sharedtodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "로그"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity - onCreate() called")


        // 유저 저장
        SharedManager.storeUserInfo("kor216456", "정대리", 20)


        get_stored_user.setOnClickListener {
            // 저장된 데이터를 가져온다

            val storedUserName = SharedManager.getUserName()

            Log.d(TAG, "MainActivity - 저장된 유저 이름: $storedUserName")

            val storedUser = SharedManager.getUser()

            Log.d(TAG, "MainActivity - 저장된 유저 - 이름: ${storedUser.name} 나이: ${storedUser.age} 아이디: ${storedUser.id}")

        }


    }




}
