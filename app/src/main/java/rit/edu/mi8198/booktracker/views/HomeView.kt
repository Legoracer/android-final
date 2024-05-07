package rit.edu.mi8198.booktracker.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import rit.edu.mi8198.booktracker.models.data.Book
import rit.edu.mi8198.booktracker.viewModels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(navigate: (String) -> Unit) {
    val viewModel = remember { HomeViewModel()}

    LaunchedEffect(key1 = "") {
        viewModel.getRecommended(viewModel.recommended)
        viewModel.getTrending("daily", viewModel.trendingDaily)
        viewModel.getTrending("monthly", viewModel.trendingMonthly)
        viewModel.getTrending("yearly", viewModel.trendingYearly)

    }

    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item { Trending("Recommended:", viewModel.recommended, navigate)}

        item { Trending("Trending today:", viewModel.trendingDaily, navigate)}

        item { Trending("Trending this month:", viewModel.trendingMonthly, navigate)}

        item { Trending("Trending this year:", viewModel.trendingYearly, navigate)}
    }
}

@Composable
fun Trending(title: String, snapshot: SnapshotStateList<Book>, navigate: (String) -> Unit) {
    Column {
        Text(title, Modifier.alpha(0.75f), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LazyRow(Modifier.height(150.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            if (snapshot.isNotEmpty()) {
                items(items = snapshot) {
                    Box(
                        Modifier
                            .height(150.dp)
                            .clickable {
                                navigate(it.key.substring(7) ?: "")
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
            } else {
                item {
                    Box(
                        Modifier
                            .height(100.dp)
                            .fillParentMaxWidth(1F)) {
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
                }
            }
        }
    }
}