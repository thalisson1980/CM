package com.example.cm.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import  com.example.cm.model.Person
import kotlinx.coroutines.flow.Flow


@Dao
interface personDao {

    @Query("SELECT * FROM person_table ORDER BY name ASC")
    fun getOrderedPeople(): Flow<List<Person>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM person_table")
    suspend fun deleteAll()
}