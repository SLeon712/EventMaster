package com.example.eventmaster.model

import kotlinx.coroutines.delay

class CategoryRepository {
    suspend fun fetchCategoryData(nombre: String, descripcion: String, icono: Int) : CategoryData{
        delay(2000)
        return CategoryData(nombre = nombre, descripcion = descripcion, iconoId = icono)
    }
}