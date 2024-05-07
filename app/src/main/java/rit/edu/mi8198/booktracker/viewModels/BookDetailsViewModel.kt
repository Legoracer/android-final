package rit.edu.mi8198.booktracker.viewModels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import rit.edu.mi8198.booktracker.models.OpenLibraryAPI
import rit.edu.mi8198.booktracker.models.data.Author
import rit.edu.mi8198.booktracker.models.data.Book
import rit.edu.mi8198.booktracker.models.repositories.BookRepository

class BookDetailsViewModel(application: Application): ViewModel() {
    private val bookRepository: BookRepository = BookRepository(application)
    val result = mutableStateOf<Book?>(null)
    val author = mutableStateOf<Author?>(null)

    fun getBook(id: String) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                result.value = null
                author.value = null
                val httpResult: Book = api.getBook(id)

                result.value = httpResult

                getAuthor(httpResult.authors.first().author.key.substring(9))

            } catch (e: Exception) {
                Log.e("MAX", e.toString())
            }
        }
    }

    fun getRoomBook(key: String): LiveData<Book> {
        val book = bookRepository.fetchBookByKey(key)

        return book
    }

    fun updatePage(key: String, page: Int){
        viewModelScope.launch {
            bookRepository.updatePage(key, page)
        }
    }

    fun getAuthor(id: String) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                author.value = null
                val httpResult: Author = api.getAuthor(id)
                author.value = httpResult
            } catch (e: Exception) {
                Log.e("MAX", e.toString())
            }
        }
    }

    fun addToFavorites(book: Book) {
        viewModelScope.launch {
            bookRepository.insertBook(book)
        }
    }

    fun removeFromFavorites(book: Book) {
        viewModelScope.launch {
            bookRepository.deleteBook(book)
        }
    }
}