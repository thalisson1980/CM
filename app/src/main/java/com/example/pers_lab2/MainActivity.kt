package com.example.pers_lab2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pers_lab2.model.Person
import com.example.pers_lab2.recycler_adpater.recyclerAdapter
import com.example.pers_lab2.viewModel.PersonViewModel
import com.example.pers_lab2.viewModel.PersonViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val newPersonActivityRequestCode = 1
    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler View
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = recyclerAdapter()
        //Merge do recycler com o adaptador para que apresente os dados
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // View Model
        //observer - mudar se for preciso e refrescar atividade
        personViewModel.allPeople.observe(this, Observer{ persons ->
            persons?.let { adapter.submitList(it) }
        })

        // Intent -> Nova atividade(insert Person)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@MainActivity, NewPersonActivity::class.java)
            startActivityForResult(intent, newPersonActivityRequestCode)
        }
    }

    // Disparado quando inserimos uma Pessoa na nova atividade
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newPersonActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(NewPersonActivity.EXTRA_REPLY)?.let { reply ->
                val array = reply.split(",")
                //array[0] - Person, array[1] - email, etc
                val person = Person(array[0], array[1], array[2].toInt(), array[3].toInt())
                personViewModel.insert(person)
            }
        }else{
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}