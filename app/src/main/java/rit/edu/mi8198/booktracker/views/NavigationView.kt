package rit.edu.mi8198.booktracker.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationView(navController: NavHostController, padding: PaddingValues) {
    Box(Modifier.padding(padding)) {
        NavHost(navController = navController, startDestination = "home") {
            // Navigation bar routes
            composable(route="home") {
                HomeView() { a:String ->
                    navController.navigate("book_details/$a")
                }
            }

            composable(route="favorites") {
                FavoritesView() { a:String ->
                    navController.navigate("favorite_book_details/$a")
                }
            }

            composable(route="search") {
                SearchView() { a:String ->
                    navController.navigate(a)
                }
            }

            // Non-navigation bar routes
            composable(route="book_details/{id}") { backStackEntry ->
                val id: String = backStackEntry.arguments?.getString("id") ?: "0"
                BookDetailsView(id) { a:String ->
                    navController.navigate("author_details/$a")
                }
            }

            composable(route="favorite_book_details/{id}") { backStackEntry ->
                val id: String = backStackEntry.arguments?.getString("id") ?: "0"
                BookDetailsView(id) { a:String ->
                    navController.navigate("author_details/$a")
                }
            }

            composable(route="author_details/{id}") { backStackEntry ->
                val id: String = backStackEntry.arguments?.getString("id") ?: "0"
                AuthorView(id) { a:String ->
                    navController.navigate("book_details/$a")
                }
            }
        }
    }
}