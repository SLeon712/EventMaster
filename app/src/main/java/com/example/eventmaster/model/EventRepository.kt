package com.example.eventmaster.repository

import com.example.eventmaster.dto.EventRequestDto
import com.example.eventmaster.model.EventData
import com.example.eventmaster.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRepository @Inject constructor(private val apiService: ApiService) {

    private val _events = MutableSharedFlow<List<EventData>>(replay = 1)
    val events: SharedFlow<List<EventData>> = _events.asSharedFlow()

    suspend fun refreshEvents() {
        try {
            val dtoList = apiService.getEvents()
            val domainList = dtoList.map { it.toDomain() }
            _events.emit(domainList)
        } catch (e: Exception) {
            e.printStackTrace()
            _events.emit(emptyList())
        }
    }

    suspend fun insertEvent(event: EventData) {
        val request = EventRequestDto(
            nombre = event.nombre,
            descripcion = event.descripcion,
            organizador = event.organizador,
            category_id = event.categoryId
        )
        apiService.createEvent(request)
        refreshEvents()
    }
}

