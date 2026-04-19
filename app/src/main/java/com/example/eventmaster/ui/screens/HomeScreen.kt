package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

    val categoryData = viewModel.categories.observeAsState()
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
            LazyColumn(
                content = {
                    items(categoryData.value ?: emptyList()){
                        Text(text = "ID: ${it.id}")
                        Text(text = "Nombre: ${it.nombre}")
                        Text(text = "Descripcion: ${it.descripcion}")
                        Button(onClick = {navController.navigate(Routes.Category+"/${it.id}")}) {
                            Text(text = "Ver Categoria ${it.nombre}")
                        }
                    }
                }
            )
        }
    }
}