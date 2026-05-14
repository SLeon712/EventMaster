package com.example.eventmaster.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
data class CategoryData(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var nombre : String,
    var descripcion : String,
    var iconoId: Int
)

data class CategoryWithEvents(
    @Embedded val categoryData: CategoryData,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val events: List<EventData>
)
