package com.example.eventmaster.remote

import com.example.eventmaster.dto.CategoryDto
import com.example.eventmaster.dto.CategoryRequestDto
import com.example.eventmaster.dto.EventDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Pide la lista de categorías a Laravel
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    // Envía una nueva categoría a Laravel
    // Nota: Como no hemos hecho el CategoryRequestDto, usamos un Map por ahora para ir rápido
    @POST("categories")
    suspend fun createCategory(@Body category: CategoryRequestDto): CategoryDto

    // Le dice a Laravel que borre una categoría por su ID
    @DELETE("categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Int)

    @GET("events")
    suspend fun getEvents(): List<EventDto>

    @POST("events")
    suspend fun createEvent(@Body event: Map<String, Any>): EventDto
}