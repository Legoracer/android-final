package rit.edu.mi8198.booktracker.views

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import rit.edu.mi8198.booktracker.models.data.Book
import rit.edu.mi8198.booktracker.models.data.Search
import rit.edu.mi8198.booktracker.viewModels.FavoritesViewModel

@Composable
fun FavoritesView(navigate: (String) -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel = FavoritesViewModel(application)
    val books = viewModel.fetchAllBooks().observeAsState(arrayListOf())

    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(items = books.value) {
            ListBook(it, navigate)
        }
    }
}

@Composable
fun ListBook(book: Book, navigate: (String) -> Unit) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
    ) {
        Box(Modifier.clickable {
            navigate(book.key.substring(7))
        }) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GlideImage(
                    imageModel = {"https://covers.openlibrary.org/b/id/${book.covers.first()}-M.jpg"},
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.CenterStart
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(0.66F, false)
                )

                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                ) {
//                    Text(book.currentPage.toString())
                    Text(book.title)
//                    Text(
//                        text = book.authors[0].author.key.toString(),
//                        modifier = Modifier
//                            .alpha(0.5F)
//                    )
                }
            }
        }
    }
}