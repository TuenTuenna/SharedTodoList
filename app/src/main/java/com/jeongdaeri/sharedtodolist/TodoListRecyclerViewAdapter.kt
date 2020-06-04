package com.jeongdaeri.sharedtodolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class TodoListRecyclerViewAdapter : RecyclerView.Adapter<TodoItemViewHolder>() {

    companion object {
        const val TAG: String = "로그"
    }

    var todoList = ArrayList<Todo>()

    // 어떤 레이아웃
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {

        // 껍데기 장착
        return TodoItemViewHolder(
            itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_todo_item, parent, false)
        )

    }

    // 목록의 갯수
    override fun getItemCount(): Int {
        return this.todoList.size
    }

    //
    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {

        Log.d(TAG, "TodoListRecyclerViewAdapter - onBindViewHolder() called / position: $position")

        //
        val todoItem = this.todoList[position]

//        val todoItem = this.todoList[0]
        holder.itemView.todo_content_text.text = todoItem.content

        holder.bindWithView(todoItem)

    }


    fun submitTodoList(todoListFromActivity: ArrayList<Todo>){
        this.todoList = todoListFromActivity
    }


}

