package rit.edu.mi8198.booktracker.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rit.edu.mi8198.booktracker.models.OpenLibraryAPI
import rit.edu.mi8198.booktracker.models.data.Author
import rit.edu.mi8198.booktracker.models.data.AuthorBooks
import rit.edu.mi8198.booktracker.models.data.Book

class AuthorViewModel: ViewModel() {
    val author = mutableStateOf<Author?>(null)
    val books = mutableStateListOf<Book>()

    fun getAuthor(id: String) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                books.clear()
                author.value = null
                val httpResult: Author = api.getAuthor(id)
                author.value = httpResult

                getBooks(id)
            } catch (e: Exception) {
                Log.e("MAX", e.toString())
            }
        }
    }

    fun getBooks(id: String) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                val httpResult: AuthorBooks = api.getAuthorBooks(id)
                books.addAll(httpResult.entries)
            } catch (e: Exception) {
                Log.e("MAX", e.toString())
            }
        }
    }
}