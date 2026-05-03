package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.R
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

/*
* Home Screen
*
* Es la pantalla de inicio, en donde se mostraran todas las categorias disponibles, acceder a ellas,
* ademas de poder crear nuevas categorias de ser necesario
* */


@Composable
fun HomeScreen(navController: NavController, viewModel: CategoryViewModel){

    val categoryData = viewModel.categoriesList.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()

    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Event Master",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primaryContainer,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { navController.navigate(Routes.CreateCategory)},
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
        ){
                Text(text = "Crear Categoria")
        }
        Spacer(modifier = Modifier.height(10.dp))
        if(isLoading.value == true){
            CircularProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                content = {
                    items(categoryData.value ?: emptyList()) { category ->
                        Button(
                            onClick = { navController.navigate(Routes.Category + "/${category.id}") },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .height(110.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = category.iconoId),
                                    modifier = Modifier.size(36.dp),
                                    contentDescription = "Icono",
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = category.nombre,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}