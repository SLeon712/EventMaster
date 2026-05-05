package com.example.eventmaster

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.ui.screens.*
import com.example.eventmaster.viewmodel.CategoryViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Routes.HomeScreen) {

        composable(Routes.HomeScreen) {
            HomeScreen(navController, categoryViewModel)
        }
        composable(Routes.CreateCategory) {
            CreateCategory(navController, categoryViewModel)
        }
        composable(Routes.Category + "/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            if (categoryId != null) Category(navController, categoryId, categoryViewModel)
        }
        composable(Routes.CreateEvent + "/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            if (categoryId != null) CreateEvent(navController, categoryViewModel, categoryId)
        }
        composable(Routes.Event + "/{categoryId}" + "/{eventId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            val eventId = backStackEntry.arguments?.getString("eventId")?.toIntOrNull()
            if (categoryId != null && eventId != null) Event(navController, categoryViewModel, categoryId, eventId)
        }
    }
}