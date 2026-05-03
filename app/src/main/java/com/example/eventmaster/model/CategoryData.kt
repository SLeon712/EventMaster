package com.example.eventmaster.model


data class CategoryData(
    var id : Int = ++lastId,
    var nombre : String,
    var descripcion : String,
    var iconoId: Int,
    var events: List<EventData> = emptyList()
){
    companion object {
        private var lastId = 0 // Tracks the last used ID
    }
}

