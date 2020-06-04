package com.jeongdaeri.sharedtodolist

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class TodoItemViewHolder(itemView: View) :
                            RecyclerView.ViewHolder(itemView),
                            View.OnLongClickListener,
                            View.OnClickListener
{


    companion object {
        const val TAG: String = "로그"
    }

    init {
        itemView.delete_todo_btn.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    private val itemTodoText = itemView.todo_content_text

    private val itemCheckBox = itemView.todo_check_box

    private val itemDeleteBtn = itemView.delete_todo_btn

    // 뷰랑 데이터 연결
    fun bindWithView(todoItem: Todo){

        itemTodoText.text = todoItem.content
        itemCheckBox.isChecked = todoItem.isDone

    }

    override fun onLongClick(view: View?): Boolean {
        Log.d(TAG, "TodoItemViewHolder - onLongClick() called")

        itemDeleteBtn.visibility = View.VISIBLE

        Toast.makeText(App.instance, "롱클릭됨", Toast.LENGTH_SHORT).show()

        return true

    }

    override fun onClick(view: View?) {
        Log.d(TAG, "TodoItemViewHolder - onClick() called")

    }


}
