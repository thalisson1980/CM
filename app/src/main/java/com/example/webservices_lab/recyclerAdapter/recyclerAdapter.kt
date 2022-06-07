package com.example.webservices_lab.recyclerAdapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.webservices_lab.API.WebServiceUserOutput
import com.example.webservices_lab.R

class recyclerAdapter (val users: List<WebServiceUserOutput>): RecyclerView.Adapter<UsersViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerline,
            parent, false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        return holder.bind(users[position])
    }
}

class UsersViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val nome: TextView = itemView.findViewById(R.id.nome)
    private val email:TextView = itemView.findViewById(R.id.email)
    private val cidade:TextView = itemView.findViewById(R.id.cidade)

    fun bind(user: WebServiceUserOutput) {
        nome.text = user.name
        cidade.text = user.address.city
        email.text = user.address.zipcode
    }

}



