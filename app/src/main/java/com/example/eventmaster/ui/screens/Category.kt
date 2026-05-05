package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(navController: NavController, categoryId: Int, viewModel: CategoryViewModel) {

    val category = viewModel.getCategoryById(categoryId).observeAsState()
    val events = viewModel.getEventsByCategory(categoryId).observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.value?.nombre ?: "Categoría", color = Color.White,
                    fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.CreateEvent + "/$categoryId") },
                containerColor = MaterialTheme.colorScheme.inversePrimary) {
                Icon(Icons.Default.Add, contentDescription = "Agregar evento", tint = Color.White)
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)
            .background(MaterialTheme.colorScheme.primary)) {

            category.value?.descripcion?.let { desc ->
                if (desc.isNotBlank()) Text(desc, color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }

            if (events.value.isNullOrEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay eventos aún.\n¡Presiona + para agregar uno!",
                        color = Color.White.copy(alpha = 0.6f), textAlign = TextAlign.Center, fontSize = 16.sp)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    item { Spacer(Modifier.height(4.dp)) }
                    items(events.value ?: emptyList()) { event ->
                        Card(
                            onClick = { navController.navigate(Routes.Event + "/$categoryId/${event.id}") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.85f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(event.titulo, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Spacer(Modifier.height(6.dp))
                                Text(event.descripcion, color = Color.White.copy(alpha = 0.75f),
                                    fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                Spacer(Modifier.height(10.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Icon(Icons.Default.DateRange, null,
                                            tint = Color.White.copy(alpha = 0.7f), modifier = Modifier.size(14.dp))
                                        Text("${event.fecha} ${event.hora}",
                                            color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Icon(Icons.Default.LocationOn, null,
                                            tint = Color.White.copy(alpha = 0.7f), modifier = Modifier.size(14.dp))
                                        Text(event.lugar, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                    item { Spacer(Modifier.height(80.dp)) }
                }
            }
        }
    }
}