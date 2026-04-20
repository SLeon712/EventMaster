package com.example.eventmaster.model

data class EventData(
    var id : Int = ++lastId,
    var nombre : String,
){
    companion object {
        private var lastId = 0 // Tracks the last used ID
    }
}