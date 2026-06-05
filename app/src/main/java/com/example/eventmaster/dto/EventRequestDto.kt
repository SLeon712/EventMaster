package com.example.eventmaster.dto

data class EventRequestDto(
    val nombre: String,
    val descripcion: String,
    val organizador: String,
    val category_id: Int
)