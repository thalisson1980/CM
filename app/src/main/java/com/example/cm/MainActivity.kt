package com.example.cm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences: SharedPreferences =  getSharedPreferences("File_1", Context.MODE_PRIVATE)

        val userInput = sharedPreferences.getString("userInput","")


        if(userInput!!.isNotEmpty()){
            findViewById<TextView>(R.id.et1).hint =  userInput;
        }else{
            findViewById<TextView>(R.id.et1).hint =  "inexistente";
        }
    }




    fun save(view: View) {
        val editTextUsername = findViewById<EditText>(R.id.et1)

        if(editTextUsername.text.isNotEmpty()){
            val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1",Context.MODE_PRIVATE)
            sharedPreference.edit()
                .putString("userInput",editTextUsername.text.toString())
                .apply()


        }else{
            Toast.makeText(this, "Write something", Toast.LENGTH_SHORT).show()
        }
    }
}

