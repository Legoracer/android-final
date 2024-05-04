package rit.edu.mi8198.booktracker.views

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import rit.edu.mi8198.booktracker.models.data.Search
import rit.edu.mi8198.booktracker.viewModels.TestViewModel

@Composable
fun SearchView(navigateTo: (String, String)->Unit ) {
    val viewModel = remember { TestViewModel() }
    var error by viewModel.error

    LaunchedEffect(key1=UInt, block={
        viewModel.search("Harry Potter", 20)
    })

    if (!error) {
        Box(Modifier.padding(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
//                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
//                        Text("Search...")
                    }
                )

                Button(onClick = { /*TODO*/ }, Modifier.padding(10.dp)) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", Modifier.size(30.dp))
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                if (viewModel.result.isEmpty()) {
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
                    items(items=viewModel.result) {
                        Box(
                            modifier = Modifier.clickable {
                                navigateTo(it.key.substring(6), it.lendingEditionS)
                            }
                        ) {
                            ListBook(it)
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

@Composable
fun ListBook(book: Search.Book) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
    ) {
        Box {
            Row(
                modifier = Modifier
                    .padding(10.dp),
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