package rit.edu.mi8198.booktracker.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationView(navController: NavHostController, padding: PaddingValues) {
    Box(Modifier.padding(padding)) {
        NavHost(navController = navController, startDestination = "search") {
            // Navigation bar routes
            composable(route="home") {
                BookDetailsView("0", "0")
            }

            composable(route="favorites") {
                BookDetailsView("0", "0")
            }

            composable(route="search") {
                SearchView() { a:String, b:String ->
                    navController.navigate("book_details/$a/$b")
                }
            }

            // Non-navigation bar routes
            composable(route="book_details/{id}/{anotherId}") { backStackEntry ->
                val id: String = backStackEntry.arguments?.getString("id") ?: "0"
                val anotherId: String = backStackEntry.arguments?.getString("anotherId") ?: "0"
                BookDetailsView(id, anotherId)
            }

            composable(route="author_details") {
                BookDetailsView("0", "0")
            }
        }
    }
}