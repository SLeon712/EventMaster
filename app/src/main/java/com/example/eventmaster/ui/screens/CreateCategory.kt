package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/*
* Create Category
*
* Pantalla para crear categorias, en caso de querer crear una categoria en la home screen,
* lo llevara a esta pantalla automaticamente para agregar los datos necesarios para la correspondiente
* nueva categoria (nombre, descripcion, color,icono,etc)
* */

@Composable
fun CreateCategory(name: String){
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pantalla Inicio y el nombre es $name")
    }
}