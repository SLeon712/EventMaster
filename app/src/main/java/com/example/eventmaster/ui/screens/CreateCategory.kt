package com.example.eventmaster.ui.screens

import android.R.id.selectedIcon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.R
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

/*
* Create Category
*
* Pantalla para crear categorias, en caso de querer crear una categoria en la home screen,
* lo llevara a esta pantalla automaticamente para agregar los datos necesarios para la correspondiente
* nueva categoria (nombre, descripcion, color,icono,etc)
* */


@Composable
fun CreateCategory(navController: NavController,categoryViewModel: CategoryViewModel){

    var nombre by remember {
        mutableStateOf("")
    }
    var descripcion by remember {
        mutableStateOf("")
    }
    val isLoading = categoryViewModel.isLoading.observeAsState()

    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Nueva Categoria", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = nombre, onValueChange = {nombre = it}, label = {Text(text = "Nombre")})
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = descripcion, onValueChange = {descripcion = it}, label = {Text(text = "Descripcion")})
        Spacer(modifier = Modifier.height(16.dp))

        val icons = listOf(R.drawable.clock,
                            R.drawable.image,
                            R.drawable.spotify,
                            R.drawable.youtube,
                            R.drawable.whatsapp)
        var selectIcon by remember { mutableStateOf(icons.first()) }
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(icons) { icon ->
                val isSelected = selectIcon == icon

                IconButton(onClick = { selectIcon = icon },
                    modifier = Modifier
                        .size(60.dp)
                        .background(color = if (isSelected) MaterialTheme.colorScheme.inversePrimary else Color(0x33000000)
                        ,shape = RoundedCornerShape(50))) {
                    Icon(painterResource(id = icon),
                        contentDescription = null,
                        tint = if (isSelected) Color.White else Color(0xFFFFDCC4)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if(isLoading.value == true){
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                categoryViewModel.addCategory(nombre, descripcion, selectIcon)
                navController.navigate(Routes.HomeScreen)
                },
                enabled = selectedIcon != null,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
            )
            {
                Text(text = "Crear Nueva categoria")
            }
        }

    }
}