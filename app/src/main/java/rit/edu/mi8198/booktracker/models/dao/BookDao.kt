package rit.edu.mi8198.booktracker.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rit.edu.mi8198.booktracker.models.data.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun fetchAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE `key` = :key")
    fun fetchBook(key: String): LiveData<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("UPDATE book SET currentPage = :page WHERE `key` = :key")
    fun updatePage(key: String, page: Int)

    @Delete
    suspend fun deleteBook(book: Book)
}