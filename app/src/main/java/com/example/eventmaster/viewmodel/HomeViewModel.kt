package com.example.eventmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.model.CategoryData
import com.example.eventmaster.model.CategoryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

class HomeViewModel : ViewModel(){
    val categoryRepository : CategoryRepository = CategoryRepository()

    private val _categories = MutableLiveData<List<CategoryData>>(emptyList())
    val categories : LiveData<List<CategoryData>> = _categories
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean> = _isLoading

    fun addCategory(nombre: String, descripcion: String){
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val newCategory = categoryRepository.fetchCategoryData(nombre,descripcion)
                val currentCategory = _categories.value ?: emptyList()
                _categories.postValue(currentCategory+ newCategory)
            } finally {
                _isLoading.postValue(false)
            }
        }

    }
}