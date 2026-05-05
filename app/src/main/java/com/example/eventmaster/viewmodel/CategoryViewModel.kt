package com.example.eventmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.db.CategoryDao
import com.example.eventmaster.db.EventDao
import com.example.eventmaster.model.CategoryData
import com.example.eventmaster.model.EventData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* CategoryViewModel - puente entre la UI y la BD.
* @HiltViewModel + @Inject hace que Hilt inyecte los DAOs automaticamente,
* sin necesitar crearlos a mano ni pasar por MainApplication.
*/
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val eventDao: EventDao
) : ViewModel() {

    val categoriesList: LiveData<List<CategoryData>> = categoryDao.getAllCategories()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _uiMessage = MutableStateFlow<String?>(null)
    val uiMessage: StateFlow<String?> = _uiMessage

    fun addCategory(nombre: String, descripcion: String, iconoId: Int, colorHex: String = "#FF6B6B") {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.addCategories(
                CategoryData(nombre = nombre, descripcion = descripcion, iconoId = iconoId, colorHex = colorHex)
            )
            _isLoading.postValue(false)
            _uiMessage.value = "Categoría creada exitosamente"
        }
    }

    fun getEventsByCategory(categoryId: Int): LiveData<List<EventData>> =
        eventDao.getEventsByCategory(categoryId)

    fun getCategoryById(categoryId: Int): LiveData<CategoryData?> =
        categoryDao.getCategoryById(categoryId)

    fun getEventById(eventId: Int): LiveData<EventData?> =
        eventDao.getEventById(eventId)

    fun addEvent(categoryId: Int, titulo: String, descripcion: String, lugar: String, fecha: String, hora: String) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            eventDao.addEvent(
                EventData(categoryId = categoryId, titulo = titulo, descripcion = descripcion,
                    lugar = lugar, fecha = fecha, hora = hora)
            )
            _isLoading.postValue(false)
            _uiMessage.value = "Evento creado exitosamente"
        }
    }

    fun clearMessage() { _uiMessage.value = null }
}