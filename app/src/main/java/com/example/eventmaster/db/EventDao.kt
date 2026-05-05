package com.example.eventmaster.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.eventmaster.model.EventData

@Dao
interface EventDao {

    @Query("SELECT * FROM EventData WHERE categoryId = :categoryId")
    fun getEventsByCategory(categoryId: Int): LiveData<List<EventData>>

    @Query("SELECT * FROM EventData WHERE id = :eventId")
    fun getEventById(eventId: Int): LiveData<EventData?>

    @Insert
    suspend fun addEvent(eventData: EventData)
}