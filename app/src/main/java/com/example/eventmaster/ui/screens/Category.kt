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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel
import com.example.eventmaster.viewmodel.EventViewModel

@Composable
fun Category(navController: NavController,id: Int?, categoryViewModel: CategoryViewModel,eventViewModel: EventViewModel){
    val category = categoryViewModel.categories.value?.find { it.id==id}

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (category != null) {
            Text(text = category.nombre, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = "Descripcion: ${category.descripcion}")
            Button(onClick = {
                navController.navigate(Routes.CreateEvent + "/${category.id}")
            }) {
                Text(text = "Crear Evento")
            }
            LazyColumn(
                content = {
                    items(category.events) {
                        Text(text = "ID: ${it.id}")
                        Text(text = "Nombre: ${it.nombre}")
                    }
                }
            )
        } else {
            Text(text = "Categoría no encontrada")
        }
    }

}
