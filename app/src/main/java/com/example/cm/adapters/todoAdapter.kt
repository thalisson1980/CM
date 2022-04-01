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
        holder.nome.text =  currentTodo.nome
        holder.idade.text = currentTodo.idade.toString()
        holder.genero.text = currentTodo.genero
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
    val nome = itemView.todoNome
    val idade = itemView.todoIdade
    val genero = itemView.todoGenero
}