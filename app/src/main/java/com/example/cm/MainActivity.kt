package com.example.cm

import android.content.Intent
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
    }

    fun changeName(view:View){
        val editText = findViewById<EditText>(R.id.ed1)
        val textView = findViewById<TextView>(R.id.tv1)

        if(editText.text.toString() == ""){
            Toast.makeText(this,"Please, write your name",Toast.LENGTH_SHORT).show()
        }else{
            textView.text = editText.text
        }



    }

    fun goToNextPage(view: View) {
        val editText = findViewById<EditText>(R.id.ed1)

        val intent = Intent(this, DashBoardActivity::class.java).apply {
            putExtra("param_name",editText.toString()) }
        startActivity(intent)
    }
}

//teste