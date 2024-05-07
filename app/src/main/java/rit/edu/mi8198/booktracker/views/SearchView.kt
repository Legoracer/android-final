package rit.edu.mi8198.booktracker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import rit.edu.mi8198.booktracker.models.data.Search
import rit.edu.mi8198.booktracker.models.data.SearchAuthors
import rit.edu.mi8198.booktracker.viewModels.SearchViewModel

@Composable
fun SearchView(navigateTo: (String) -> Unit) {
    val viewModel = remember { SearchViewModel() }
    var error by viewModel.error
    var searchText by remember { mutableStateOf("") }

    var isSearchingAuthors by remember { mutableStateOf(false) }

    val books = viewModel.books
    val authors = viewModel.authors
    val searchingAuthors by viewModel.searchingAuthors
    val searchingBooks by viewModel.searchingBooks

    if (!error) {
        Column(Modifier.padding(10.dp)) {
            // Search bar
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {searchText = it},
//                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Search...")
                    },
                    keyboardOptions = KeyboardOptions.Default
                )

                Button(onClick = {
                    viewModel.search(searchText, 20)
                    viewModel.searchAuthors(searchText, 20)
                }, Modifier.padding(10.dp)) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", Modifier.size(30.dp))
                }
            }

            // Toggle
            Row(Modifier.fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.fillMaxWidth(0.5f)) {
                    Card(
                        Modifier.fillMaxWidth(1f)
                            .clickable {
                                isSearchingAuthors = false
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (!isSearchingAuthors) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                        ),
                        ) {
                        Text("Books", Modifier.fillMaxWidth(1f), textAlign = TextAlign.Center, fontSize = 20.sp)
                    }

                }

                Column(Modifier.fillMaxWidth(1f).padding(top = 10.dp)) {
                    Card(
                        Modifier.fillMaxWidth(1f)
                            .clickable {
                                isSearchingAuthors = true
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSearchingAuthors) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                        ),
                    ) {
                        Text("Author", Modifier.fillMaxWidth(1f), textAlign = TextAlign.Center, fontSize = 20.sp)
                    }
                }
            }

            // Search Results
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(top=10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (!isSearchingAuthors && searchingBooks || isSearchingAuthors && searchingAuthors) {
                    // LOADING
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(1F),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.width(64.dp),
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }
                    }
                } else {
                    if (!isSearchingAuthors) {
                        // books
                        items(items=books) {
                            Box(
                                modifier = Modifier.clickable {
                                    navigateTo("book_details/" + it.key.substring(7))
                                }
                            ) {
                                ListBook(it)
                            }
                        }
                    } else {
                        // authors
                        items(items=authors) {
                            Box(
                                modifier = Modifier.clickable {
                                    navigateTo("author_details/" + it.key)
                                }
                            ) {
                                ListAuthor(it)
                            }
                        }
                    }
                }
            }
        }
    } else {
        ErrorModal(
            onDismissRequest = { error = false },
            onConfirmation = {
                error = false
                viewModel.search("Harry Potter", 20)
            },
            dialogTitle = "API Error",
            dialogText = "We have encountered an error while fetching information from the API",
            dialogConfirm = "Retry",
            dialogDismiss = "Dismiss",
            icon = null
        )
    }
}

// if (searching) {

//
//                    }
//                } else {
//
//                }

@Composable
fun ListBook(book: Search.Book) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
    ) {
        Box {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GlideImage(
                    imageModel = {"https://covers.openlibrary.org/b/id/${book.coverImageID}-M.jpg"},
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
                    Text(book.title)
                    Text(
                        text = book.authorName[0],
                        modifier = Modifier
                            .alpha(0.5F)
                    )
                }
            }
        }
    }
}


@Composable
fun ListAuthor(author: SearchAuthors.Doc) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
    ) {
        Box {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GlideImage(
                    imageModel = {"https://st3.depositphotos.com/9998432/13335/v/450/depositphotos_133352010-stock-illustration-default-placeholder-man-and-woman.jpg"},
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.CenterStart
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1F, false)
                )

                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                ) {
                    Text(author.name)
                }
            }
        }
    }
}