package com.example.webservices_lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webservices_lab.API.EndPoints
import com.example.webservices_lab.API.OutputPost
import com.example.webservices_lab.API.ServiceBuilder
import com.example.webservices_lab.API.WebServiceUserOutput
import com.example.webservices_lab.recyclerAdapter.recyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUsers()

        call.enqueue(object: Callback<List<WebServiceUserOutput>> {
            override fun onResponse(
                call: Call<List<WebServiceUserOutput>>,
                response: Response<List<WebServiceUserOutput>>
            ) {
                if (response.isSuccessful){
                    val recView = findViewById<RecyclerView>(R.id.recyclerView)
                        recView.layoutManager = LinearLayoutManager(this@MainActivity)
                        recView.adapter = recyclerAdapter(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<WebServiceUserOutput>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getSingle(view: View) {

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUserById(2)

        call.enqueue(object: Callback<WebServiceUserOutput>{
            override fun onResponse(call: Call<WebServiceUserOutput>, response: Response<WebServiceUserOutput>) {
                if(response.isSuccessful){
                    val c: WebServiceUserOutput = response.body()!!
                    Toast.makeText(this@MainActivity, c.address.zipcode, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<WebServiceUserOutput>, t: Throwable){
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
    fun post(view: View) {

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.postTest("Post Succeeded")

        call.enqueue(object: Callback<OutputPost>{
            override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>){
                if(response.isSuccessful){
                    val c: OutputPost = response.body()!!
                    Toast.makeText(this@MainActivity, c.id.toString() + "-" + c.title, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<OutputPost>, t: Throwable){
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}