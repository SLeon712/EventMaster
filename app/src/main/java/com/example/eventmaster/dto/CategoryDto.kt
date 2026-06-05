package com.example.eventmaster.dto

import com.example.eventmaster.R
import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("descripcion") val descripcion: String?,
    // Laravel suele usar snake_case para las bases de datos
    @SerializedName("icono_id") val iconoId: Int?
) {
    // Función opcional para convertir el DTO al modelo que usa tu UI
    fun toDomain() = com.example.eventmaster.model.CategoryData(
        id = id ?: 0,
        nombre = nombre ?: "",
        descripcion = descripcion ?: "",
        iconoId = if (iconoId != null && iconoId != 0) iconoId else R.drawable.image
    )
}
