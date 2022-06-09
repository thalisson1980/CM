package com.example.pers_lab2.repositorio

import androidx.annotation.WorkerThread
import com.example.pers_lab2.dao.PersonDao
import com.example.pers_lab2.model.Person
import kotlinx.coroutines.flow.Flow


class PersonRepository(private val personDao: PersonDao){

    //Variavel que vai ao Dao chamar o metodo getOrdered...
    val allPeople: Flow<List<Person>> = personDao.getOrderedPeople()

    val bPeople: Flow<List<Person>> = personDao.getB()

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun delete20(){
        personDao.delete20()
    }

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun insert(person: Person){
        personDao.insert(person)
    }

}