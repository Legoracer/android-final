package rit.edu.mi8198.booktracker.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import rit.edu.mi8198.booktracker.viewModels.BookDetailsModel

@Composable
fun BookDetailsView(id: String, anotherId: String) {
    val viewModel = BookDetailsModel()
    val bookResults = remember { viewModel.books }
    val pageResults = remember { viewModel.pages }

    LaunchedEffect(key1=UInt, block={
        viewModel.getBook(id)
        viewModel.getPageCount(anotherId)
    })

    if (bookResults.isNotEmpty()) {
        if (pageResults.isNotEmpty()) {
            Text(bookResults.first().title)
            Text(pageResults.first().toString())
        } else {
            Text("Im actually empty so your hypothesis was wrong nigger... again")
        }
    } else {
        Text("Im actually empty so your hypothesis was wrong nigger")
    }
}