package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

/*
* Create Category
*
* Pantalla para crear categorias, en caso de querer crear una categoria en la home screen,
* lo llevara a esta pantalla automaticamente para agregar los datos necesarios para la correspondiente
* nueva categoria (nombre, descripcion, color,icono,etc)
* */


@Composable
fun CreateCategory(navController: NavController,categoryViewModel: CategoryViewModel){

    var nombre by remember {
        mutableStateOf("")
    }
    var descripcion by remember {
        mutableStateOf("")
    }
    val isLoading = categoryViewModel.isLoading.observeAsState()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Nueva Categoria", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = nombre, onValueChange = {nombre = it}, label = {Text(text = "Nombre")})
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = descripcion, onValueChange = {descripcion = it}, label = {Text(text = "Descripcion")})
        Spacer(modifier = Modifier.height(16.dp))


        if(isLoading.value == true){
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                categoryViewModel.addCategory(nombre, descripcion)
                navController.navigate(Routes.HomeScreen)
            }) {
                Text(text = "Crear Nueva categoria")
            }
        }

    }
}