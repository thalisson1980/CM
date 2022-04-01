package com.example.cm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cm.adapters.todoAdapter
import com.example.cm.models.todo
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: todoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = todoAdapter(ArrayList())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        val btnAddTodo = R.id.btnAddTodo
        btnAddTodo.setOnClickListener {
            val todoTitle = etTodo.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = todo(todoTitle, LocalDate.now())

                todoAdapter.addTodo(todo)
                etTodo.text.clear()
            }
        }
    }
}

