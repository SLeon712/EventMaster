package com.example.eventmaster.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombre: String,
    var descripcion: String,
    var iconoId: Int,
    var colorHex: String = "#FF6B6B"
)