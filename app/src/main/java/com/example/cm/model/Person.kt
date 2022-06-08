package com.example.cm.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
class Person (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "idade")
    val idade: Int,
    @ColumnInfo(name = "email")
    val email: String
)
