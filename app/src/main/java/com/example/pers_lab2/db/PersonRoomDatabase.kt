package com.example.pers_lab2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pers_lab2.dao.PersonDao
import com.example.pers_lab2.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.security.AccessControlContext

@Database(entities = [Person::class],  version = 2, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    private class PersonDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.personDao())
                }
            }
        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.personDao())
                }
            }
        }

        suspend fun populateDatabase(personDao: PersonDao) {

            personDao.deleteAll()

            var person = Person("Gonçalo", "gurjais@ipvc.pt", 21)
            personDao.insert(person)
            person = Person("Luís", "lf@ipvc.pt", 21)
            personDao.insert(person)

            person = Person("Joel", "js@ipvc,.pt", 21)
            personDao.insert(person)
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        //Vai buscar a instancia da bd
        fun getDatabase(context: Context, scope: CoroutineScope): PersonRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    "personDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(PersonDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
