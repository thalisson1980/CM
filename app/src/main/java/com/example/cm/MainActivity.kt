package com.example.cm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Bem vindo", Toast.LENGTH_SHORT).show()
    }

    fun pagar(view:View){
        val editText = findViewById<EditText>(R.id.ed1)
        val textView = findViewById<TextView>(R.id.tv1)
        val editText2 = findViewById<EditText>(R.id.ed2)

        textView.text = (Integer.parseInt(editText.text.toString()) * Integer.parseInt(editText2.text.toString()) * 1.23).toString()

    }
}

