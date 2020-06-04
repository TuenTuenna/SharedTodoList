package com.jeongdaeri.sharedtodolist

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class TodoItemViewHolder(itemView: View,
                        todoRecyclerviewInterface: TodoRecyclerviewInterface
                        ) :
                            RecyclerView.ViewHolder(itemView),
                            View.OnLongClickListener,
                            View.OnClickListener,
                            CompoundButton.OnCheckedChangeListener
{


    companion object {
        const val TAG: String = "로그"
    }

    private var todoRecyclerviewInterface : TodoRecyclerviewInterface

    init {

        itemView.delete_todo_btn.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
        itemView.todo_check_box.setOnCheckedChangeListener(this)

        this.todoRecyclerviewInterface = todoRecyclerviewInterface
    }

    private val itemTodoText = itemView.todo_content_text

    private val itemCheckBox = itemView.todo_check_box

    private val itemDeleteBtn = itemView.delete_todo_btn

    // 뷰랑 데이터 연결
    fun bindWithView(todoItem: Todo){

        itemTodoText.text = todoItem.content
        itemCheckBox.isChecked = todoItem.isDone

        if(todoItem.isDone){
            itemTodoText.paintFlags = itemTodoText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

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

    // 체크박스 체크 여부
    override fun onCheckedChanged(checkBox: CompoundButton?, isChecked: Boolean) {
        Log.d(TAG, "TodoItemViewHolder - onCheckedChanged() called ")

        if(checkBox == itemCheckBox){
            Log.d(TAG, "TodoItemViewHolder - isChecked : $isChecked")

            // 인터페이스를 통해서 즉 액티비티에 어떤 녀석이 변경되었는지 알려준다.
            this.todoRecyclerviewInterface.onTodoItemChanged(adapterPosition, isChecked)

            if(isChecked == true){
                itemTodoText.paintFlags = itemTodoText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                itemTodoText.paintFlags = 0
            }

        }

    }


}
