package com.example.pers_lab2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewPersonActivity : AppCompatActivity() {

    private lateinit var editPersonView: EditText
    private lateinit var editEmailView: EditText
    private lateinit var editAgeView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_person)
        editPersonView = findViewById(R.id.edit_person)
        editEmailView = findViewById(R.id.email)
        editAgeView = findViewById(R.id.age)

//Quando se carrega no botão de gravar
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editPersonView.text) && TextUtils.isEmpty(editEmailView.text) && TextUtils.isEmpty(editAgeView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val person = editPersonView.text.toString()
                val email = editEmailView.text.toString()
                val age = editAgeView.text.toString()

//Permite passar info para a atividade que chamou esta
                replyIntent.putExtra(EXTRA_REPLY, person.plus(",$email").plus(",$age"))
                setResult(Activity.RESULT_OK, replyIntent)
            }
            //Ao fazer o finish vai disparar o metodo onActivityResult presente na MainActivity
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.personlistsql.REPLY"
    }
}