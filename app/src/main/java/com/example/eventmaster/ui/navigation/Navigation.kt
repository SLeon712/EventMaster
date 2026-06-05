package com.example.eventmaster

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.ui.screens.Category
import com.example.eventmaster.ui.screens.HomeScreen
import com.example.eventmaster.ui.screens.CreateCategory
import com.example.eventmaster.ui.screens.CreateEvent
import com.example.eventmaster.ui.screens.Event
import com.example.eventmaster.viewmodel.CategoryViewModel

/*
* Navigation
*
* Archivo kotlin que funciona como controlador de navegacion entre pantallas, cada vez que quieras
* crear una nueva pantalla y agregarle datos a esta, es necesario agregarla aca primero, creando
* un composable con la ruta de esta.
* */

@Composable
fun Navigation(){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.HomeScreen){
        composable(Routes.HomeScreen){
            HomeScreen(navController)
        }
        composable(Routes.CreateCategory){
            CreateCategory(navController)
        }
        composable(Routes.Category+"/{categoryId}"){ backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            if (categoryId != null)
                Category(navController,categoryId)
        }
        composable(Routes.CreateEvent + "/{categoryId}"){ backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            if (categoryId != null)
                CreateEvent(navController,categoryId)
        }
        composable(Routes.Event + "/{categoryId}" + "/{eventId}"){ backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            val EventId = backStackEntry.arguments?.getString("eventId")?.toIntOrNull()
            if (categoryId != null && EventId != null)
                Event(categoryId,EventId)
        }
    }
}