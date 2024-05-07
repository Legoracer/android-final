package rit.edu.mi8198.booktracker.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rit.edu.mi8198.booktracker.models.OpenLibraryAPI
import rit.edu.mi8198.booktracker.models.data.Book
import rit.edu.mi8198.booktracker.models.data.Search
import rit.edu.mi8198.booktracker.models.data.Trending

class HomeViewModel: ViewModel() {
    val recommended = mutableStateListOf<Book>()
    val trendingDaily = mutableStateListOf<Book>()
    val trendingMonthly = mutableStateListOf<Book>()
    val trendingYearly = mutableStateListOf<Book>()

    fun getRecommended(bookList: MutableList<Book>) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()
            val books: List<String> = listOf("OL1168083W", "OL64468W", "OL17091839W", "OL166894W", "OL471576W", "OL17267881W", "OL2466636W", "OL47773856M", "OL257943W", "OL152258W")

            try {

                books.map { id ->
                    api.getBook(id)
                }.also { newBooks ->
                    bookList.addAll(newBooks)
                }

            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error getting recommended books", e)
            }
        }
    }

    fun getTrending(period: String, bookList: MutableList<Book>) {
        viewModelScope.launch {
            val api = OpenLibraryAPI.getInstance()

            try {
                val httpResult: Trending = api.getTrending(period)
                httpResult.works.map { work ->
                    api.getBook(work.key.substring(7))
                }.also { newBooks ->
                    bookList.addAll(newBooks)
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error getting trending books for $period", e)
            }
        }
    }
}