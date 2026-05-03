package com.example.eventmaster.ui.screens
/*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.model.EventData
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

/*
* Create Event
*
* Archivo kotlin que funciona como pantalla para crear eventos.
* */


@Composable
fun CreateEvent(navController: NavController, categoryViewModel: CategoryViewModel, categoryId: Int){

    var nombre by remember {
        mutableStateOf("")
    }
    var descripcion by remember {
        mutableStateOf("")
    }
    var organizador by remember {
        mutableStateOf("")
    }

    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Nuevo Evento", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(22.dp))

        TextField(value = nombre, onValueChange = {nombre = it}, label = {Text(text = "Nombre")})
        Spacer(modifier = Modifier.height(22.dp))

        TextField(value = descripcion, onValueChange = {descripcion = it}, label = {Text(text = "descripcion")})
        Spacer(modifier = Modifier.height(22.dp))

        TextField(value = organizador, onValueChange = {organizador = it}, label = {Text(text = "organizador")})
        Spacer(modifier = Modifier.height(22.dp))

        Button(onClick = {
            val newEvent = EventData(nombre = nombre, descripcion = descripcion, organizador = organizador)
            // categoryViewModel.addEventToCategory(categoryId, newEvent)
            navController.navigate(Routes.Category + "/${categoryId}")
        }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary))
        {
            Text(text = "Crear Evento")
        }

    }
}

 */