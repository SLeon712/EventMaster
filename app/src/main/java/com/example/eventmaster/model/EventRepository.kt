package com.example.eventmaster.model

import kotlinx.coroutines.delay

class EventRepository {
    suspend fun fetchEventData(nombre: String) : EventData{
        delay(2000)
        return EventData(nombre = nombre)
    }
}