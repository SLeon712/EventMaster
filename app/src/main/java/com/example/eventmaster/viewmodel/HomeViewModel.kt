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

    private val _categoryData = MutableLiveData<CategoryData>()
    val categoryData : LiveData<CategoryData> = _categoryData
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean> = _isLoading

    fun createCategory(nombre: String, descripcion: String){
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val categoryResult = categoryRepository.fetchCategoryData(nombre,descripcion)
                _categoryData.postValue(categoryResult)
            } finally {
                _isLoading.postValue(false)
            }
        }

    }
}