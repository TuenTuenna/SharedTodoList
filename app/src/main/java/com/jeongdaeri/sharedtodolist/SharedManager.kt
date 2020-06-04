package com.jeongdaeri.sharedtodolist

import android.content.Context
import android.util.Log
import com.google.gson.Gson


// 싱글턴 적용
object SharedManager {


    private const val TAG: String = "로그"

    private const val SHARED_TODO_LIST = "shared_todo_list"
    private const val KEY_TODO_LIST = "key_todo_list"


    // 데이터 저장
    fun storeUserInfo(id: String, userName: String, userAge: Int){

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // 쉐어드에 에디터 가져오기
        val editor = shared.edit()

        // 에디터에 데이터를 넣고
        // 키, 값
        editor.putString("user_id", id)
        editor.putString("user_name", userName)
        editor.putInt("user_age", userAge)

        // 에디터 변경 사항을 적용
        editor.apply()
    }


    fun storeUser(user: User) {

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // 쉐어드에 에디터 가져오기
        val editor = shared.edit()

        // 에디터에 데이터를 넣고
        // 키, 값
        editor.putString("user_id", user.id)
        editor.putString("user_name", user.name)
        editor.putInt("user_age", user.age!!)

        // 에디터 변경 사항을 적용
        editor.apply()
    }


    // 사용자 모델 가져오기
    fun getUser(): User {
        //쉐어드 가져오기
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val storedUserName = shared.getString("user_name", "")!!

        val storedUserId = shared.getString("user_id", "")!!

        val storedUserAge = shared.getInt("user_age", 0)

        val storedUser = User(storedUserId, storedUserName, storedUserAge)

        return storedUser
    }


    fun getUserName() : String {

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val storeUserName = shared.getString("user_name", "")!!

        return storeUserName

    }


    // 할일 목록 저장
    fun storeTodoList(todoList: MutableList<Todo>){
        Log.d(TAG, "SharedManager - storeTodoList() called")

        // 문자열로 변환된 배열
        val todoListString = Gson().toJson(todoList)

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_TODO_LIST, Context.MODE_PRIVATE)

        // 쉐어드에 에디터 가져오기
        val editor = shared.edit()

        // 에디터에 데이터를 넣고
        // 키, 값
        editor.putString(KEY_TODO_LIST, todoListString)

        // 에디터 변경 사항을 적용
        editor.apply()
    }




    // 할일 목록 가져오기
    fun getTodoList() : MutableList<Todo> {
        Log.d(TAG, "SharedManager - storeTodoList() called")

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_TODO_LIST, Context.MODE_PRIVATE)

        // 저장되어있던 문자열
        val storedTodoListString = shared.getString(KEY_TODO_LIST, "")!!


        // 가져온 문자열 배열로 변환
        var storedTodoList = ArrayList<Todo>()


        if(storedTodoListString.isNotEmpty()){

            storedTodoList = Gson().fromJson(storedTodoListString, Array<Todo>::class.java).toMutableList() as ArrayList<Todo>
        }

        return storedTodoList
    }














}
