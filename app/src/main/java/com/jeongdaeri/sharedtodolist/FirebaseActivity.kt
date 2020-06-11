package com.jeongdaeri.sharedtodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_firebase.*

class FirebaseActivity : AppCompatActivity(), TodoRecyclerviewInterface, ValueEventListener {


    companion object {
        const val TAG: String = "로그"
    }


    private lateinit var database: DatabaseReference

    private lateinit var todoReference: DatabaseReference

    private var myTodoList = ArrayList<Todo>()

    private lateinit var todoListRecyclerViewAdapter: TodoListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        Log.d(TAG, "FirebaseActivity - onCreate() called")

        database = FirebaseDatabase.getInstance().reference

        todoReference = database.child("todos")


        // 리사이클러뷰 어답터 준비
        todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter(this)


        todoReference.addValueEventListener(this)




//        val valueEventListener = object : ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//
//            }
//
//        }
//
//        todoReference.addValueEventListener(valueEventListener)


        // 데이터 변경에 대한 리스너 설정 - 파이어베이스
//        todoReference.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                //
//                for (todoSnapshot in dataSnapshot.children) {
//
//                    val todoItem = todoSnapshot.getValue(Todo::class.java) as Todo
//
//                    myTodoList.add(todoItem)
//
//                    Log.d(TAG, "FirebaseActivity - todoItem.content : ${todoItem.content}")
//                }
//                todoListRecyclerViewAdapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                // ...
//            }
//        })



        // 기관총에 장전함
        todoListRecyclerViewAdapter.todoList = myTodoList

        // 기관총에 장전함
//        todoListRecyclerViewAdapter.submitTodoList(myTodoList)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        linearLayoutManager.stackFromEnd = true

        todo_list_recycler_view.apply {
            adapter = todoListRecyclerViewAdapter
            layoutManager = linearLayoutManager

            this.scrollToPosition(todoListRecyclerViewAdapter.itemCount - 1)
        }

        add_todo_btn.setOnClickListener {

            val contentString = new_todo_edit_text.text.toString()

            addNewTodo(contentString)

            new_todo_edit_text.setText("")
        }


        new_todo_edit_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                Log.d("텍스트입력", "FirebaseActivity - afterTextChanged() called")
                if(!new_todo_edit_text.text.isNullOrEmpty()){
                    add_todo_btn.visibility = View.VISIBLE
                } else {
                    add_todo_btn.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("텍스트입력", "FirebaseActivity - beforeTextChanged() called")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("텍스트입력", "FirebaseActivity - onTextChanged() called")
            }

        })


    }

    private fun addNewTodo(content: String) {

        val newTodo = Todo(content)
        database.child("todos").push().setValue(newTodo).addOnSuccessListener {
            Log.d(TAG, "FirebaseActivity - 할일 추가 성공")
        }.addOnFailureListener {
            Log.d(TAG, "FirebaseActivity - 할일 추가 실패 ")
        }
    }



    // 아이템 값 변경  - 파이어베이스
    private fun updateTodoItem(uid: String, isDone: Boolean){
//        database.child("todos").child(uid).child("done").setValue(isDone)
        todoReference.child(uid).child("done").setValue(isDone)
    }


    // 아이템 변경
    override fun onTodoItemChanged(position: Int, isDone: Boolean) {
        Log.d(TAG, "FirebaseActivity - onTodoItemChanged() called / position : $position")

        val selectedItemUidString = this.myTodoList[position].uid as String

        // 파이어베이스 아이템 값 설정
        updateTodoItem(selectedItemUidString, isDone)

    }

    // 리사이크럴뷰 인터페이스 - 아이템 삭제 버튼 클릭
    override fun onTodoItemDeleted(position: Int) {
        Log.d(TAG, "FirebaseActivity - onTodoItemDeleted() called / position: $position")

        val selectedItemUidString = this.myTodoList[position].uid as String

//        todoReference.child(uid).child("done").setValue(isDone)
        database.child("todos").child(selectedItemUidString).removeValue().addOnSuccessListener {
            Toast.makeText(this, "성공적으로 삭제 했습니다.", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Log.d(TAG, "FirebaseActivity - 실패 : it.printStackTrace() : ${it.printStackTrace()}")
        }

    }

    //
    override fun onCancelled(databaseError: DatabaseError) {
        Log.d(TAG, "FirebaseActivity - onCancelled() called")
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {

        this.myTodoList.clear()

        for (todoSnapshot in dataSnapshot.children) {

            val todoItem = todoSnapshot.getValue(Todo::class.java) as Todo

            todoItem.uid = todoSnapshot.key

            this.myTodoList.add(todoItem)

            Log.d(TAG, "FirebaseActivity - todoItem.content : ${todoItem.content}")
            Log.d(TAG, "FirebaseActivity - todoItem.uid : ${todoItem.uid}")
        }
        this.todoListRecyclerViewAdapter.notifyDataSetChanged()
    }


}
