package com.example.eventmaster.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.eventmaster.model.CategoryData
import com.example.eventmaster.model.CategoryWithEvents
import java.util.Locale

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryData")
    fun getAllCategories() : LiveData<List<CategoryData>>

    @Insert
    fun addCategories(categoryData: CategoryData)

    @Transaction
    @Query("SELECT * FROM categorydata")
    fun getCategoriesWithEvents(): LiveData<List<CategoryWithEvents>>
}