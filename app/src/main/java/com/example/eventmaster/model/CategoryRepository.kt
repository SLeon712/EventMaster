package com.example.eventmaster.model

import com.example.eventmaster.db.CategoryDao
import jakarta.inject.Inject
import kotlinx.coroutines.delay

class CategoryRepository @Inject constructor(
private val categoryDao: CategoryDao
) {
    fun getAllCategories() = categoryDao.getAllCategories()
    fun getCategoriesWithEvents() = categoryDao.getCategoriesWithEvents()
    suspend fun insertCategory(category: CategoryData) = categoryDao.addCategories(category)
}