package com.example.eventmaster.repository

import com.example.eventmaster.dto.CategoryRequestDto
import com.example.eventmaster.model.CategoryData
import com.example.eventmaster.remote.ApiService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val apiService: ApiService
) {
    private val _categories = MutableSharedFlow<List<CategoryData>>(replay = 1)
    val categories: SharedFlow<List<CategoryData>> = _categories.asSharedFlow()

    suspend fun refreshCategories() {
        try {
            val dtoList = apiService.getCategories()
            val domainList = dtoList.map { it.toDomain() }
            _categories.emit(domainList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun insertCategory(category: CategoryData) {
        apiService.createCategory(CategoryRequestDto(category.nombre, category.descripcion, category.iconoId))
    }

    suspend fun deleteCategory(category: CategoryData) {
        try {
            apiService.deleteCategory(category.id)
            refreshCategories()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
