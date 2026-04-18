package com.example.eventmaster

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.ui.screens.HomeScreen
import com.example.eventmaster.ui.screens.CreateCategory

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
    NavHost(navController = navController, startDestination = Routes.HomeScreen, builder = {
        composable(Routes.HomeScreen){
            HomeScreen(navController)
        }
        composable(Routes.CreateCategory+"/{name}"){
            val name = it.arguments?.getString("name")
            CreateCategory(name ?: "No name")
        }
    })
}