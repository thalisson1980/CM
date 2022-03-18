package com.example.cm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val parametro = intent.getStringExtra("param_name")

        val textView = findViewById<TextView>(R.id.tv1DashBoard)

        textView.text = parametro
    }
}