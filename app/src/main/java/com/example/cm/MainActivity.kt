package com.example.cm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cm.adapters.todoAdapter
import com.example.cm.models.todo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: todoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = todoAdapter(ArrayList())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)


        btnAddTodo.setOnClickListener {
            val todoNome = etNome.text.toString()
            val todoIdade = Integer.valueOf(etIdade.text.toString())
            val todoGenero =  "masculino"
            if(todoNome.isNotEmpty()){
                val todo = todo(todoNome, todoIdade,todoGenero)

                todoAdapter.addTodo(todo)
                etNome.text.clear()
                //setContentView(R.layout.list)
            }
        }
    }
}

