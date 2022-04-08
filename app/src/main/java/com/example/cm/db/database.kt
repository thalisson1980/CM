package com.example.cm.db
import android.content.Context
import androidx.room.*
import com.example.cm.dao.personDao
import com.example.cm.model.Person

@Database(entities = [ Person::class], version = 1, exportSchema = false)
abstract class database: RoomDatabase() {

    abstract fun personDao(): personDao

    companion object {
        @Volatile
        private var INSTANCE: database? = null

        fun getDatabase(context: Context): database {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "person_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}