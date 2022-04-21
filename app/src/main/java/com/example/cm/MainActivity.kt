package com.example.cm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.menuex3,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        return when (item.itemId) {
            R.id.item1 ->{
                val editText = findViewById<EditText>(R.id.ed1)
                val textView = findViewById<TextView>(R.id.tv1)
                val editText2 = findViewById<EditText>(R.id.ed2)
                editText.text.clear()
                textView.text = ""
                editText2.text.clear()
                true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}

