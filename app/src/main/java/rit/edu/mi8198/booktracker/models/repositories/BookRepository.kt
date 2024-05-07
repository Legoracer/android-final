package rit.edu.mi8198.booktracker.models.repositories

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import rit.edu.mi8198.booktracker.models.AppDatabase
import rit.edu.mi8198.booktracker.models.dao.BookDao
import rit.edu.mi8198.booktracker.models.data.Book

class BookRepository(application: Application) {
    private var bookDao: BookDao

    init {
        val database = AppDatabase.getDatabase(application)
        bookDao = database.bookDao()
    }

    fun fetchAllBooks(): LiveData<List<Book>> = bookDao.fetchAllBooks()
    fun fetchBook(book: Book): LiveData<Book> = bookDao.fetchBook(book.key)
    fun fetchBookByKey(key: String): LiveData<Book> = bookDao.fetchBook(key)

    fun updatePage(key: String, page: Int)= bookDao.updatePage(key, page)


    suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }
}