package com.jeongdaeri.sharedtodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_todo_list.*

class TodoListActivity : AppCompatActivity(), TodoRecyclerviewInterface {


    companion object {
        const val TAG: String = "로그"
    }


    private var myTodoList = ArrayList<Todo>()

    private lateinit var todoListRecyclerViewAdapter: TodoListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        Log.d(TAG, "TodoListActivity - onCreate() called")


        // 버튼
        add_todo_btn.setOnClickListener {
            Log.d(TAG, "TodoListActivity - 할일 추가 버튼 클릭")

            val newTodoContentText = new_todo_edit_text.text.toString()

            val newTodo = Todo(newTodoContentText)

            new_todo_edit_text.setText("")

            //
            myTodoList.add(newTodo)

            // 추가 데이터 저장
            SharedManager.storeTodoList(myTodoList)

            todoListRecyclerViewAdapter.notifyDataSetChanged()

            todo_list_recycler_view.scrollToPosition(todoListRecyclerViewAdapter.itemCount - 1)

        }




        // 저장된 목록 가져오기
        myTodoList = SharedManager.getTodoList() as ArrayList<Todo>

        // 리사이클러뷰 어답터 준비
        todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter(this)

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

//        todo_list_recycler_view.adapter = todoListRecyclerViewAdapter
//        todo_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



    }

    // 특정 할일 아이템이 변경되었다.
    override fun onTodoItemChanged(position: Int, isDone: Boolean) {
        Log.d(TAG, "TodoListActivity - onTodoItemChanged() called / position: $position / isDone: $isDone")

        this.myTodoList[position].isDone = isDone

        // 변경된 배열을 저장한다
        SharedManager.storeTodoList(this.myTodoList)

    }

    override fun onTodoItemDeleted(position: Int) {

    }


}
