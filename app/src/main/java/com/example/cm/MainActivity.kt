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
    private val aux = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = todoAdapter(ArrayList())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        for(i in 1 until 11){
            val todo = todo("país "+(aux-i), "Capital $i",i*100,i*10,i*20)
            todoAdapter.addTodo(todo )

        }

    }
}

