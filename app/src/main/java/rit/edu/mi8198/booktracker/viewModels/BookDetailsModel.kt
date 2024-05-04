package rit.edu.mi8198.booktracker.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rit.edu.mi8198.booktracker.models.OpenLibraryAPI
import rit.edu.mi8198.booktracker.models.data.Book
import rit.edu.mi8198.booktracker.models.data.BookWhichIOnlyNeedForPageCount

class BookDetailsModel: ViewModel() {
    private val _result = mutableStateListOf<Book>()
    private val _result2 = mutableStateListOf<Int>()

    val books: List<Book>
        get() = _result

    val pages: List<Int>
        get() = _result2

    fun getBook(id: String) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                _result.clear()
                val httpResult: Book = api.getBook(id)
                _result.add(httpResult)
            } catch (e: Exception) {
                Log.e("MAX", e.toString())
            }
        }
    }

    fun getPageCount(anotherId: String) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                _result2.clear()
                val httpResult: BookWhichIOnlyNeedForPageCount = api.getBookAnother(anotherId)
                _result2.add(httpResult.numberOfPages)
            } catch (e: Exception) {
                Log.e("MAX", e.toString())
            }
        }
    }
}