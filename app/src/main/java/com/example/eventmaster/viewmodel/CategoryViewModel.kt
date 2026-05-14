package com.example.eventmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.MainApplication
import com.example.eventmaster.db.CategoryDao
import com.example.eventmaster.model.CategoryData
import com.example.eventmaster.model.CategoryRepository
import com.example.eventmaster.model.CategoryWithEvents
import com.example.eventmaster.model.EventData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
//import com.example.eventmaster.model.EventData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
* Category ViewModel
*
* clase viewmodel de categoria como puente para añadir categorias desde las pantallas hacia categoryData,
* tambien sirve para añadir eventos desde las pantallas a EventData.
* */
@HiltViewModel
class CategoryViewModel @Inject constructor(
private val repository: CategoryRepository
) : ViewModel(){
    val categoryDao = MainApplication.eventMasterDatabase.getCategoryDao()
    val categoriesList: StateFlow<List<CategoryData>> = repository.getAllCategories()
    .stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = emptyList()
    )

    val categoriesWithEvents: StateFlow<List<CategoryWithEvents>> = repository.getCategoriesWithEvents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val eventDao = MainApplication.eventMasterDatabase.getEventDao()
    val eventsList: LiveData<List<EventData>> = eventDao.getAllEvents()

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean> = _isLoading



    fun addCategory(nombre: String, descripcion: String, iconoId : Int){
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.addCategories(CategoryData(nombre = nombre, descripcion = descripcion, iconoId = iconoId))
            _isLoading.postValue(false)
        }


    }
    fun addEventToCategory(categoryId: Int, event: EventData) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            eventDao.addEvents(event.copy(categoryId = categoryId))
            _isLoading.postValue(false)
        }
    }
}