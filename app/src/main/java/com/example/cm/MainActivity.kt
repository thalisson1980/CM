package com.example.cm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

import com.example.cm.models.todo
import kotlinx.android.synthetic.main.activity_main.*

import java.time.LocalDate

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddTodo.setOnClickListener {
            val todoNome = etNome.text.toString()
            val todoIdade = etIdade.text.toString()
            val todoGenero =  "masculino"
            if(todoNome.isNotEmpty()){
                val intent = Intent(this, listActivity::class.java).apply {
                    putExtra("nome",todoNome)
                    putExtra("idade",todoIdade)
                    putExtra("genero",todoGenero)}
                startActivity(intent)

            }
        }
    }



}

