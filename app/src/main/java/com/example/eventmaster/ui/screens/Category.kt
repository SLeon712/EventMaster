package com.example.eventmaster.ui.screens

import android.R
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel
import com.example.eventmaster.viewmodel.EventViewModel

@Composable
fun Category(
    navController: NavController,
    id: Int?,
    categoryViewModel: CategoryViewModel,
    eventViewModel: EventViewModel
){
    val category = categoryViewModel.categories.value?.find { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light)),
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
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Button(
                        onClick = {
                            navController.navigate(Routes.CreateEvent + "/${category.id}")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF65A30D),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Crear Evento")
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.heightIn(max = 300.dp)
                    ) {
                        items(category.events) { event ->

                            Button(
                                onClick = {
                                    navController.navigate(Routes.Event + "/${category.id}" + "/${event.id}")
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFD6B4AF),
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(text = event.nombre)
                            }
                        }
                    }
                }

        } else {
            Text(text = "Categoria no encontrada")
        }
    }
}

