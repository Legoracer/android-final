package rit.edu.mi8198.booktracker.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import rit.edu.mi8198.booktracker.models.data.Search
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rit.edu.mi8198.booktracker.models.OpenLibraryAPI

class TestViewModel: ViewModel() {
    private val _result = mutableStateListOf<Search.Book>()

    var error = mutableStateOf(false)

    val result: List<Search.Book>
        get() = _result
    fun search(title: String, limit: Int) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                _result.clear()
                error.value = false
                val httpResult: Search = api.search(title, limit)
                Log.d("test", result.size.toString())
                _result.addAll(httpResult.books)
            } catch (e: Exception) {
                error.value = true
                Log.e("MAX", e.toString())
            }
        }
    }
}