package rit.edu.mi8198.booktracker.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import rit.edu.mi8198.booktracker.models.data.Search
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rit.edu.mi8198.booktracker.models.OpenLibraryAPI
import rit.edu.mi8198.booktracker.models.data.SearchAuthors

class SearchViewModel: ViewModel() {
    private val _books = mutableStateListOf<Search.Book>()
    private val _authors = mutableStateListOf<SearchAuthors.Doc>()

    var error = mutableStateOf(false)

    var searchingBooks = mutableStateOf(false)
    var searchingAuthors = mutableStateOf(false)

    val books: List<Search.Book>
        get() = _books

    val authors: List<SearchAuthors.Doc>
        get() = _authors

    fun search(title: String, limit: Int) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                _books.clear()
                searchingBooks.value = true
                error.value = false
                val httpResult: Search = api.search(title, limit)
                _books.addAll(httpResult.books)
                searchingBooks.value = false
            } catch (e: Exception) {
                searchingBooks.value = false
                error.value = true
                Log.e("MAX", e.toString())
            }
        }
    }

    fun searchAuthors(name: String, limit: Int) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                _authors.clear()
                searchingAuthors.value = true
                error.value = false
                val httpResult: SearchAuthors = api.searchAuthors(name, limit)
                _authors.addAll(httpResult.docs)
                searchingAuthors.value = false
            } catch (e: Exception) {
                searchingAuthors.value = false
                error.value = true
                Log.e("MAX", e.toString())
            }
        }
    }
}