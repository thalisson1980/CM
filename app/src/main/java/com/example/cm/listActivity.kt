package com.example.cm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cm.adapters.todoAdapter
import com.example.cm.models.todo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list.*
import java.time.LocalDate

class listActivity : AppCompatActivity() {

    private lateinit var todoAdapter: todoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        todoAdapter = todoAdapter(ArrayList())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        val nome = intent.getStringExtra("nome")
        val idade = intent.getStringExtra("idade")
        val genero = intent.getStringExtra("genero")
        val todo = todo(nome.toString(),Integer.parseInt(idade),genero.toString())
        todoAdapter.addTodo(todo)

    }
}