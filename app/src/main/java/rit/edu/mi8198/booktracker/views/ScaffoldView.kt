package rit.edu.mi8198.booktracker.views

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScaffoldView() {
    val navController = rememberNavController()

    var isDarkTheme by remember { mutableStateOf(false) }

    ThemeView(isDarkTheme = isDarkTheme) {
        Scaffold(
            bottomBar = { BottomBar(navController = navController) {
                isDarkTheme = !isDarkTheme
            } }
        ) { padding ->
            NavigationView(navController = navController, padding = padding)
        }
    }
}