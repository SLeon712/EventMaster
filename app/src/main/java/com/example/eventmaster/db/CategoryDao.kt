package com.example.eventmaster.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.example.eventmaster.model.CategoryData



@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryData")
    fun getAllCategories(): LiveData<List<CategoryData>>

    @Query("SELECT * FROM CategoryData WHERE id = :categoryId")
    fun getCategoryById(categoryId: Int): LiveData<CategoryData?>

    @Insert
    suspend fun addCategories(categoryData: CategoryData)
}