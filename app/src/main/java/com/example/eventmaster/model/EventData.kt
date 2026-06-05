package com.example.eventmaster.model

data class EventData(
    val id: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val categoryId: Int = 0,
    val organizador: String = ""
)
