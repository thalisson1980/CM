package com.example.cm.model

import androidx.room.*

@Entity(tableName = "person_table")
data class Person(val name: String,) {
}