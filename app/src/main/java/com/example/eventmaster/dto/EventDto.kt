package com.example.eventmaster.dto

import com.example.eventmaster.model.EventData
import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("id")          val id: Int?,
    @SerializedName("nombre")      val nombre: String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("category_id") val categoryId: Int?,
    @SerializedName("organizador") val organizador: String?
) {
    fun toDomain() = EventData(
        id         = id ?: 0,
        nombre     = nombre ?: "",
        descripcion = descripcion ?: "",
        categoryId = categoryId ?: 0,
        organizador = organizador ?: ""
    )
}
