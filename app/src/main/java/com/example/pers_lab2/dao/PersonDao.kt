package com.example.pers_lab2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.pers_lab2.model.Person
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {

    @Query("SELECT * FROM person_table ORDER BY name ASC")
    //Flow: notificar em tempo real
    fun getOrderedPeople(): Flow<List<Person>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM person_table")
    suspend fun deleteAll()

    @Query("DELETE FROM person_table WHERE age > 20")
    suspend fun delete20(): Flow<List<Person>>

    @Query("SELECT * FROM person_table where name like 'b%'")
    fun getB(): Flow<List<Person>>

}