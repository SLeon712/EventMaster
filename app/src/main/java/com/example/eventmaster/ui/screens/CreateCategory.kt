package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.R
import com.example.eventmaster.ui.components.EventTextField
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCategory(navController: NavController, categoryViewModel: CategoryViewModel) {

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var nombreError by remember { mutableStateOf<String?>(null) }
    val isLoading = categoryViewModel.isLoading.observeAsState()

    val icons = listOf(R.drawable.clock, R.drawable.image, R.drawable.spotify,
        R.drawable.youtube, R.drawable.whatsapp)
    var selectedIcon by remember { mutableStateOf(icons.first()) }

    val colorOptions = listOf("#FF6B6B","#4ECDC4","#45B7D1","#96CEB4",
        "#FFEAA7","#DDA0DD","#98D8C8","#F7DC6F")
    var selectedColor by remember { mutableStateOf(colorOptions.first()) }

    fun validateAndSubmit() {
        nombreError = when {
            nombre.isBlank() -> "El nombre no puede estar vacío"
            nombre.length < 3 -> "Mínimo 3 caracteres"
            nombre.length > 30 -> "Máximo 30 caracteres"
            else -> null
        }
        if (nombreError == null) {
            categoryViewModel.addCategory(nombre.trim(), descripcion.trim(), selectedIcon, selectedColor)
            navController.navigate(Routes.HomeScreen) { popUpTo(Routes.HomeScreen) { inclusive = true } }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Categoría", color = Color.White, fontWeight = FontWeight.Bold) },
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
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))
            EventTextField(value = nombre, onValueChange = { nombre = it; nombreError = null },
                label = "Nombre de la categoría *", errorMessage = nombreError)
            Spacer(Modifier.height(12.dp))
            EventTextField(value = descripcion, onValueChange = { descripcion = it },
                label = "Descripción (opcional)", singleLine = false, maxLines = 3)
            Spacer(Modifier.height(24.dp))

            Text("Elige un icono", color = Color.White, fontWeight = FontWeight.Medium,
                fontSize = 16.sp, modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp))
            Spacer(Modifier.height(12.dp))
            LazyRow(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(icons) { icon ->
                    IconButton(onClick = { selectedIcon = icon },
                        modifier = Modifier.size(60.dp).background(
                            color = if (selectedIcon == icon) MaterialTheme.colorScheme.inversePrimary
                            else Color(0x33FFFFFF), shape = RoundedCornerShape(50))) {
                        Icon(painterResource(id = icon), contentDescription = null, tint = Color.White)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Elige un color", color = Color.White, fontWeight = FontWeight.Medium,
                fontSize = 16.sp, modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp))
            Spacer(Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                colorOptions.forEach { colorHex ->
                    val color = Color(android.graphics.Color.parseColor(colorHex))
                    Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(color)
                        .then(if (selectedColor == colorHex) Modifier.border(3.dp, Color.White, CircleShape) else Modifier)
                        .clickable { selectedColor = colorHex })
                }
            }

            Spacer(Modifier.height(40.dp))
            if (isLoading.value == true) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
            } else {
                Button(onClick = { validateAndSubmit() },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)) {
                    Text("Crear Categoría", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
