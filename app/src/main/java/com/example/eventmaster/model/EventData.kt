package com.example.eventmaster.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventData(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var nombre : String,
    var descripcion : String,
    var organizador : String,
    var categoryId: Int
    )
