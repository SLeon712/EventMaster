package com.example.eventmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.R
import com.example.eventmaster.viewmodel.CategoryViewModel
import com.example.eventmaster.viewmodel.EventViewModel

@Composable
fun Event(
    navController: NavController,
    categoryViewModel: CategoryViewModel,
    categoryId: Int?,
    eventViewModel: EventViewModel,
    eventId: Int?
){
    val backgroundColor = colorResource(id = R.color.secondary_light)
    val category = categoryViewModel.categories.value?.find { it.id == categoryId }
    val event = category?.events?.find { it.id == eventId }

    if (event != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(android.R.color.background_light)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = event.nombre,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    } else {
            Text(text = "Evento no encontrado")
    }

}