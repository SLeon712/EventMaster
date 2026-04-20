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
import com.example.eventmaster.viewmodel.EventViewModel

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
    val categoryViewModel: CategoryViewModel = viewModel()
    val eventViewModel: EventViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.HomeScreen, builder = {
        composable(Routes.HomeScreen){
            HomeScreen(navController,categoryViewModel)
        }
        composable(Routes.CreateCategory){
            CreateCategory(navController,categoryViewModel)
        }
        composable(Routes.Category+"/{categoryId}"){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            Category(navController,id,categoryViewModel,eventViewModel)
        }
        composable(Routes.CreateEvent + "/{categoryId}"){ backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            if (categoryId != null)
                CreateEvent(navController,categoryViewModel,categoryId)
        }
        composable(Routes.Event){
            Event()
        }
    })
}