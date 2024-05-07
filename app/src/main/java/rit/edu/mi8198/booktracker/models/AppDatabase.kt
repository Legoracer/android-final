package rit.edu.mi8198.booktracker.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rit.edu.mi8198.booktracker.models.dao.BookDao
import rit.edu.mi8198.booktracker.models.data.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book_db_5",
                ).allowMainThreadQueries().build()

                instance = newInstance
                return newInstance
            }
        }
    }
}