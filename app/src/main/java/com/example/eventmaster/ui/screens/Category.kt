package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

/*
* Category
*
* Archivo kotlin que funciona para pantalla para ver una categoria seleccionada y los eventos que contiene,
* ademas de dar la posibilidad de crear mas eventos
* */

@Composable
fun Category(
    navController: NavController,
    id: Int?,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
){
    val categoriesList = categoryViewModel.categoriesList.collectAsStateWithLifecycle()
    // Ya no usamos categoriesWithEvents, ahora usaremos solo la lista de eventos
    val eventsList = categoryViewModel.eventsList.collectAsStateWithLifecycle()

    val category = categoriesList.value.find { it.id == id }
    // Filtramos los eventos manualmente que pertenecen a esta categoría
    val filteredEvents = eventsList.value.filter { it.categoryId == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (category != null) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = category.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = category.descripcion,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate(Routes.CreateEvent + "/${category.id}") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Crear Evento", color = MaterialTheme.colorScheme.onPrimary)
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.heightIn(max = 300.dp)
                ) {
                    // Ahora recorremos los eventos filtrados
                    items(filteredEvents) { event ->
                        Button(
                            onClick = { navController.navigate(Routes.Event + "/${category.id}" + "/${event.id}") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(text = event.nombre, color = MaterialTheme.colorScheme.onSecondary)
                        }
                    }
                }
            }
        } else {
            Text(text = "Categoría no encontrada", color = MaterialTheme.colorScheme.error)
        }
    }
}

