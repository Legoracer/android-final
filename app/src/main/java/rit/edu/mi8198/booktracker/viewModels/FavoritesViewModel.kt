package rit.edu.mi8198.booktracker.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rit.edu.mi8198.booktracker.models.data.Book
import rit.edu.mi8198.booktracker.models.repositories.BookRepository

class FavoritesViewModel(application: Application): ViewModel() {
    private val bookRepository: BookRepository = BookRepository(application)

    fun fetchAllBooks(): LiveData<List<Book>> {
        val books = bookRepository.fetchAllBooks()
        return books
    }

    fun insertBook(book: Book) {
        viewModelScope.launch {
            bookRepository.insertBook(book)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            bookRepository.deleteBook(book)
        }
    }
}