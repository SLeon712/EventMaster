package com.example.eventmaster.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.model.CategoryData
import com.example.eventmaster.model.CategoryWithEvents
import com.example.eventmaster.model.EventData
import com.example.eventmaster.repository.CategoryRepository
import com.example.eventmaster.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    val categoriesList: StateFlow<List<CategoryData>> = categoryRepository.categories
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val eventsList: StateFlow<List<EventData>> = eventRepository.events
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val isLoading = MutableLiveData(false)

    val categoriesWithEvents: StateFlow<List<CategoryWithEvents>> =
        combine(categoriesList, eventsList) { cats, evts ->
            cats.map { cat ->
                CategoryWithEvents(
                    categoryData = cat,
                    events = evts.filter { it.categoryId == cat.id }
                )
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            refreshCategories()
            refreshEvents()
        }
    }

    fun refreshCategories() {
        viewModelScope.launch {
            isLoading.value = true
            categoryRepository.refreshCategories()
            isLoading.value = false
        }
    }

    fun refreshEvents() {
        viewModelScope.launch {
            eventRepository.refreshEvents()
        }
    }

    fun addCategory(nombre: String, descripcion: String, iconoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val category = CategoryData(nombre = nombre, descripcion = descripcion, iconoId = iconoId)
            categoryRepository.insertCategory(category)
            refreshCategories()
        }
    }

    fun addEventToCategory(categoryId: Int, event: EventData) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.insertEvent(event)
            // Esto obliga a refrescar la lista después de la inserción
            eventRepository.refreshEvents()
        }
    }
}
