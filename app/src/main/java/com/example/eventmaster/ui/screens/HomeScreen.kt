package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: CategoryViewModel) {

    val categoryData = viewModel.categoriesList.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.CreateCategory) },
                containerColor = MaterialTheme.colorScheme.inversePrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Crear categoria", tint = Color.White)
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .background(MaterialTheme.colorScheme.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text("EventMaster", fontSize = 36.sp, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primaryContainer)
            Text("Tus categorias de eventos", fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.6f), modifier = Modifier.padding(top = 4.dp))
            Spacer(modifier = Modifier.height(24.dp))

            when {
                isLoading.value == true -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
                    }
                }
                categoryData.value.isNullOrEmpty() -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No hay categorias aun.\n¡Presiona + para crear una!",
                            color = Color.White.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center, fontSize = 16.sp)
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)
                    ) {
                        items(categoryData.value ?: emptyList()) { category ->
                            Button(
                                onClick = { navController.navigate(Routes.Category + "/${category.id}") },
                                modifier = Modifier.fillMaxWidth().padding(8.dp).height(110.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.inversePrimary)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(painter = painterResource(id = category.iconoId),
                                        modifier = Modifier.size(36.dp),
                                        contentDescription = null, tint = Color.White)
                                    Spacer(Modifier.height(8.dp))
                                    Text(category.nombre, fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium, color = Color.White,
                                        textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
