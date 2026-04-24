package com.example.eventmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.model.EventData
import com.example.eventmaster.model.EventRepository
import kotlinx.coroutines.launch

class EventViewModel : ViewModel(){
    val eventRepository : EventRepository = EventRepository()

    private val _events = MutableLiveData<List<EventData>>(emptyList())
    val events : LiveData<List<EventData>> = _events
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean> = _isLoading

    fun addEvent(nombre: String,descripcion: String,organizador: String){
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val newEvent = eventRepository.fetchEventData(nombre, descripcion, organizador)
                val currentEvent = _events.value ?: emptyList()
                _events.postValue(currentEvent+ newEvent)
            } finally {
                _isLoading.postValue(false)
            }
        }

    }
}