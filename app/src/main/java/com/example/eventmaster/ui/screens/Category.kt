package com.example.eventmaster.ui.screens
/*
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventmaster.ui.navigation.Routes
import com.example.eventmaster.viewmodel.CategoryViewModel

/*
* Category
*
* Archivo kotlin que funciona para pantalla para ver una categoria seleccionada y los eventos que contiene,
* ademas de dar la posibilidad de crear mas eventos
* */

@Composable
fun Category(
    navController: NavController,
    id: Int?,
    categoryViewModel: CategoryViewModel,
){
    val category = categoryViewModel.categoriesList.value?.find { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light))
            .background(MaterialTheme.colorScheme.primary),
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
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                    Text(
                        text = category.descripcion,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.primaryContainer
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Button(
                        onClick = {
                            navController.navigate(Routes.CreateEvent + "/${category.id}")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.inversePrimary
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
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
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

*/