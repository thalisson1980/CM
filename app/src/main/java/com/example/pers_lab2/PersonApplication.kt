package com.example.pers_lab2

import android.app.Application
import com.example.pers_lab2.db.PersonRoomDatabase
import com.example.pers_lab2.repositorio.PersonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PersonApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { PersonRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PersonRepository(database.personDao()) }

}

