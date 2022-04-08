package com.example.cm.repository

import androidx.annotation.WorkerThread
import com.example.cm.dao.personDao
import com.example.cm.model.Person
import kotlinx.coroutines.flow.Flow

class personRepository(private val personDao: personDao) {

    val allPeople: Flow<List<Person>> = personDao.getOrderedPeople()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.insert(person)
    }
}