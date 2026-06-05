package com.example.eventmaster.model

import com.example.eventmaster.R

data class CategoryData(
    var id: Int = 0,
    var nombre: String = "",
    var descripcion: String = "",
    var iconoId: Int = R.drawable.image
)

data class CategoryWithEvents(
    val categoryData: CategoryData,
    val events: List<EventData>
)
