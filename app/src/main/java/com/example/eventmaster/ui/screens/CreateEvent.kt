package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.ui.components.EventTextField
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEvent(navController: NavController, categoryViewModel: CategoryViewModel, categoryId: Int) {

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }

    var tituloError by remember { mutableStateOf<String?>(null) }
    var descripcionError by remember { mutableStateOf<String?>(null) }
    var lugarError by remember { mutableStateOf<String?>(null) }
    var fechaError by remember { mutableStateOf<String?>(null) }
    var horaError by remember { mutableStateOf<String?>(null) }

    val isLoading = categoryViewModel.isLoading.observeAsState()
    val category = categoryViewModel.getCategoryById(categoryId).observeAsState()

    fun isValidDate(input: String) = Regex("""^\d{2}/\d{2}/\d{4}$""").matches(input)
    fun isValidTime(input: String) = Regex("""^\d{2}:\d{2}$""").matches(input)

    fun validateAndSubmit() {
        tituloError = when {
            titulo.isBlank() -> "El título es obligatorio"
            titulo.length < 3 -> "Mínimo 3 caracteres"
            titulo.length > 50 -> "Máximo 50 caracteres"
            else -> null
        }
        descripcionError = when {
            descripcion.isBlank() -> "La descripción es obligatoria"
            descripcion.length < 5 -> "Mínimo 5 caracteres"
            else -> null
        }
        lugarError = if (lugar.isBlank()) "El lugar es obligatorio" else null
        fechaError = when {
            fecha.isBlank() -> "La fecha es obligatoria"
            !isValidDate(fecha) -> "Formato: DD/MM/AAAA"
            else -> null
        }
        horaError = when {
            hora.isBlank() -> "La hora es obligatoria"
            !isValidTime(hora) -> "Formato: HH:MM"
            else -> null
        }
        if (listOf(tituloError, descripcionError, lugarError, fechaError, horaError).all { it == null }) {
            categoryViewModel.addEvent(categoryId, titulo.trim(), descripcion.trim(),
                lugar.trim(), fecha.trim(), hora.trim())
            navController.navigate(Routes.Category + "/$categoryId") {
                popUpTo(Routes.Category + "/$categoryId") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Evento en ${category.value?.nombre ?: "..."}",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)
            .background(MaterialTheme.colorScheme.primary).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(Modifier.height(24.dp))
            EventTextField(titulo, { titulo = it; tituloError = null }, "Título del evento *", errorMessage = tituloError)
            Spacer(Modifier.height(12.dp))
            EventTextField(descripcion, { descripcion = it; descripcionError = null }, "Descripción *",
                errorMessage = descripcionError, singleLine = false, maxLines = 4)
            Spacer(Modifier.height(12.dp))
            EventTextField(lugar, { lugar = it; lugarError = null }, "Lugar *", errorMessage = lugarError)
            Spacer(Modifier.height(12.dp))
            EventTextField(fecha, { fecha = it; fechaError = null }, "Fecha (DD/MM/AAAA) *", errorMessage = fechaError)
            Spacer(Modifier.height(12.dp))
            EventTextField(hora, { hora = it; horaError = null }, "Hora (HH:MM) *", errorMessage = horaError)
            Spacer(Modifier.height(40.dp))

            if (isLoading.value == true) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
            } else {
                Button(onClick = { validateAndSubmit() },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)) {
                    Text("Guardar Evento", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}