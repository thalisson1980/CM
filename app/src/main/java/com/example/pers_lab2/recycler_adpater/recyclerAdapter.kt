package com.example.pers_lab2.recycler_adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pers_lab2.R
import com.example.pers_lab2.model.Person
import java.util.*

class recyclerAdapter : ListAdapter<Person , recyclerAdapter.PersonViewHolder>(PersonComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val current = getItem(position)
        var estado = "Ja fez anos";
        var anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        if(anoAtual > (current.year + current.age)){
            estado = "Vai fazer anos";
        }


        holder.bind(current.name, current.age.toString(),estado)
    }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val personItemView: TextView = itemView.findViewById(R.id.name)
        private val ageItemView: TextView = itemView.findViewById(R.id.age)
        private val estadoItemView: TextView = itemView.findViewById(R.id.estado)

        fun bind(text: String?, age:String?,estado: String?) {
            personItemView.text = text
            ageItemView.text = age
            ageItemView.text = estado
        }

        companion object {
            fun create(parent: ViewGroup): PersonViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return PersonViewHolder(view)
            }
        }
    }

    class PersonComparator : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
