package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.HomeViewModel

/*
* Home Screen
*
* Es la pantalla de inicio, en donde se mostraran todas las categorias disponibles, acceder a ellas,
* ademas de poder crear nuevas categorias de ser necesario
* */

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel){

    val categoryData = viewModel.categoryData.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Event Master Pantalla")
        Button(onClick = {
            navController.navigate(Routes.CreateCategory)
        }) {
            Text(text = "Crear Categoria")
        }
        if(isLoading.value == true){
            CircularProgressIndicator()
        } else {
            categoryData.value?.nombre?.let {
                Text(text = "Nombre Es $it")
            }
            categoryData.value?.descripcion?.let {
                Text(text = it)
            }
        }
    }
}