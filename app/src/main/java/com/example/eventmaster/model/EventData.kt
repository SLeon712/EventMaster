package com.example.eventmaster.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/*
* EventData - entidad Room para los eventos.
* CASCADE en onDelete significa que si borras la categoria, se borran sus eventos tambien.
*/
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CategoryData::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EventData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var categoryId: Int,
    var titulo: String,
    var descripcion: String,
    var lugar: String,
    var fecha: String,
    var hora: String
)