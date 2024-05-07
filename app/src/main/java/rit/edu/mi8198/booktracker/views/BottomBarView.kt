package rit.edu.mi8198.booktracker.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController, toggleDarkTheme: ()->Unit) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = {
                   Icon(
                       imageVector = if (currentRoute == "home")
                           Icons.Outlined.Home else Icons.Default.Home,
                       contentDescription = "Home"
                   )
            },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = if (currentRoute == "favorites")
                        Icons.Outlined.Star else Icons.Default.Star,
                    contentDescription = "Favorites"
                )
            },
            label = { Text("Favorites") },
            selected = currentRoute == "favorites",
            onClick = {
                navController.navigate("favorites") {
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = if (currentRoute == "search")
                        Icons.Outlined.Search else Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            label = { Text("Search") },
            selected = currentRoute == "search",
            onClick = {
                navController.navigate("search") {
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = if (currentRoute == "search")
                        Icons.Outlined.Face else Icons.Default.Face,
                    contentDescription = "Search"
                )
            },
            label = { Text("Dark Mode") },
            selected = false,
            onClick = {
                toggleDarkTheme()
            }
        )
    }
}