package com.example.cm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cm.R
import com.example.cm.models.todo
import kotlinx.android.synthetic.main.todo_line.view.*

class todoAdapter(private val todos: ArrayList<todo>):RecyclerView.Adapter<todoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        return todoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_line,parent,false)
        )
    }

    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.title.text =  currentTodo.title
        holder.date.text = currentTodo.date.toString()
        holder.number.text = "#" + (position + 1).toString()
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo(todo: todo){
        todos.add(todo)
        notifyDataSetChanged()
    }

}
class todoViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    val title = itemView.todoTitle
    val date = itemView.todoDate
    val number = itemView.todoNumber
}