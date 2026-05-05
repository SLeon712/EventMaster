package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Event(navController: NavController, viewModel: CategoryViewModel, categoryId: Int, eventId: Int) {

    val event = viewModel.getEventById(eventId).observeAsState()
    val category = viewModel.getCategoryById(categoryId).observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(event.value?.titulo ?: "Detalle", color = Color.White,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->

        if (event.value == null) {
            Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
            }
            return@Scaffold
        }

        val e = event.value!!

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)
            .background(MaterialTheme.colorScheme.primary).padding(16.dp)) {

            category.value?.let { cat ->
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 12.dp)) {
                    Icon(painterResource(id = cat.iconoId), null,
                        tint = Color.White.copy(alpha = 0.7f), modifier = Modifier.size(18.dp))
                    Text(cat.nombre, color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
                }
            }

            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.85f))) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(e.titulo, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Spacer(Modifier.height(16.dp))
                    Text("Descripción", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(e.descripcion, color = Color.White, fontSize = 15.sp, lineHeight = 22.sp)
                    Spacer(Modifier.height(20.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                        Column {
                            Text("Fecha", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                            Spacer(Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Icon(Icons.Default.DateRange, null, tint = Color.White, modifier = Modifier.size(16.dp))
                                Text(e.fecha, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                        Column {
                            Text("Hora", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                            Spacer(Modifier.height(4.dp))
                            Text(e.hora, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    Text("Lugar", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.LocationOn, null, tint = Color.White, modifier = Modifier.size(16.dp))
                        Text(e.lugar, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}

