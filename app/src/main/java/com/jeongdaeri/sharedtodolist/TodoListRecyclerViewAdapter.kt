package com.jeongdaeri.sharedtodolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class TodoListRecyclerViewAdapter(todoRecyclerviewInterface: TodoRecyclerviewInterface)
                        : RecyclerView.Adapter<TodoItemViewHolder>() {

    companion object {
        const val TAG: String = "로그"

        const val ME_SEND: Int = 0
        const val OTHER_SEND: Int = 1
        const val ADVERTISE: Int = 2

    }

    var todoList = ArrayList<Todo>()

    private var todoRecyclerviewInterface : TodoRecyclerviewInterface? = null

    init {
        Log.d(TAG, "TodoListRecyclerViewAdapter - init() called")
        this.todoRecyclerviewInterface = todoRecyclerviewInterface

    }



    // 어떤 레이아웃
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {


        when(viewType){
            ME_SEND -> {
                // 내가 보낸 레이아웃
            }
            OTHER_SEND -> {
                //
            }
            ADVERTISE -> {

            }
        }

        // 껍데기 장착
        return TodoItemViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_todo_item, parent, false)
        , todoRecyclerviewInterface = this.todoRecyclerviewInterface!!)

    }

    // 목록의 갯수
    override fun getItemCount(): Int {
        return this.todoList.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }

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

