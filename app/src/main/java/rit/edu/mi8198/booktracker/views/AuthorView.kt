package rit.edu.mi8198.booktracker.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import rit.edu.mi8198.booktracker.viewModels.AuthorViewModel
import rit.edu.mi8198.booktracker.viewModels.BookDetailsViewModel

@Composable
fun AuthorView(id: String, navigateTo: (String) -> Unit) {
    val viewModel = remember { AuthorViewModel() }
    val author by viewModel.author

    LaunchedEffect(key1=UInt, block={
        viewModel.getAuthor(id)
    })

    if (author != null) {
        LazyColumn(Modifier.padding(10.dp)) {
            item {
                Row(Modifier.height(100.dp)) {
                    val coverId: Int? = author?.photos?.first()
                    if (coverId != null) {
                        GlideImage(
                            imageModel = {"https://covers.openlibrary.org/b/id/${coverId.toString()}-M.jpg"},
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.FillBounds,
                                alignment = Alignment.CenterStart
                            ),
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1F, false)
                        )
                    }

                    Column(Modifier.padding(10.dp)) {
                        author?.name?.let { Text(it) }
                        author?.birthDate?.let { Text(it) }
                    }
                }

                Text("Bio:", Modifier.alpha(0.75F).padding(PaddingValues(top = 10.dp)))
                author?.bio?.let {
                    val s = if (it.length > 300) it.substring(0, 300) + "..." else it;
                    Text(s.replace(Regex("\r?\n"), " "))
                }

                Text("Works:", Modifier.alpha(0.75F).padding(PaddingValues(top = 10.dp)))
                LazyRow(Modifier.height(100.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (viewModel.books.isNotEmpty()) {
                        items(items = viewModel.books) {
                            Box(Modifier.height(100.dp).clickable {
                                navigateTo(it.key.substring(7) ?: "")
                            }) {
                                val cover = it?.covers?.first()
                                GlideImage(
                                    imageModel = { if (cover != null) "https://covers.openlibrary.org/b/id/${cover}-M.jpg" else "https://openlibrary.org/images/icons/avatar_book-sm.png" },
                                    imageOptions = ImageOptions(
                                        contentScale = ContentScale.FillBounds,
                                        alignment = Alignment.CenterStart
                                    ),
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .aspectRatio(0.66F, false)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}