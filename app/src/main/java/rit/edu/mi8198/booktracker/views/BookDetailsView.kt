package rit.edu.mi8198.booktracker.views

import android.app.Application
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.room.InvalidationTracker
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import okhttp3.internal.notifyAll
import rit.edu.mi8198.booktracker.viewModels.BookDetailsViewModel

@Composable
fun BookDetailsView(id: String, navigateTo: (String) -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel = remember { BookDetailsViewModel(application) }

    var currentPage by remember { mutableStateOf("0") }
    var triggerRecomposition by remember { mutableStateOf(0) }
    val book by viewModel.result
    val author by viewModel.author
    val roomBook by viewModel.getRoomBook("/works/${id}").observeAsState()

    LaunchedEffect(key1 = UInt, block = {
        viewModel.getBook(id)
    })

    LaunchedEffect(triggerRecomposition) {
        // This block will run every time triggerRecomposition changes
    }


    if (book != null) {
        LazyColumn(Modifier.padding(10.dp)) {
            item {
                Row(Modifier.height(150.dp)) {
                    val coverId: Int? = book?.covers?.first()
                    if (coverId != null) {
                        GlideImage(
                            imageModel = { "https://covers.openlibrary.org/b/id/${coverId.toString()}-M.jpg" },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.FillBounds,
                                alignment = Alignment.CenterStart
                            ),
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(0.66F, false)
                        )
                    }

                    Column(Modifier.padding(10.dp)) {
                        book?.title?.let { Text(it) }
                        if (author != null) {
                            TextButton(onClick = {
                                navigateTo(book?.authors?.first()?.author?.key?.substring(9) ?: "")
                            }) {
                                Text(author?.name ?: "Unknown")
                            }
                        }
                    }
                }

                Text(
                    "Subjects:",
                    Modifier
                        .alpha(0.75F)
                        .padding(PaddingValues(top = 10.dp))
                )
                book?.subjects?.let {
                    var s = ""
                    for (i in 0..Math.max(0, Math.min(it.size - 1, 3))) {
                        s += ", " + (it.get(i) ?: "Unknown")
                    }
                    Text(s.substring(2))
                }

                Text(
                    "Description:",
                    Modifier
                        .alpha(0.75F)
                        .padding(PaddingValues(top = 10.dp))
                )
                book?.actualDescription?.let {
                    val s = if (it.length > 300) it.substring(0, 300) + "..." else it;
                    Text(s.replace(Regex("\r?\n"), " "))
                }

                Column(
                    Modifier
                        .padding(PaddingValues(top = 25.dp))
                        .fillParentMaxWidth(1F),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    if (roomBook != null) {
                        Text("Current page: ${roomBook!!.currentPage}")



                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(value = currentPage, onValueChange = {
                                currentPage = it
                            })
                            IconButton(
                                onClick = {
                                    val n = try {
                                        Integer.parseInt(currentPage)
                                    } catch (e: Exception) {
                                        0
                                    }
                                    roomBook!!.currentPage = n
                                    viewModel.addToFavorites(roomBook!!)
                                    triggerRecomposition++
                                },
                                Modifier.background(MaterialTheme.colorScheme.primary).fillMaxHeight()
                            ) {
                                Icon(Icons.Default.Done, contentDescription = null)
                            }
                        }


                        Button(onClick = {
                            viewModel.removeFromFavorites(book!!)
                        }) {
                            Text(
                                "Remove from Favorites",
                                Modifier.fillParentMaxWidth(1F),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        Button(onClick = {
                            viewModel.addToFavorites(book!!)
                        }) {
                            Text(
                                "Add to Favorites",
                                Modifier.fillParentMaxWidth(1F),
                                textAlign = TextAlign.Center
                            )
                        }
                    }


                }
            }
        }
    }
}