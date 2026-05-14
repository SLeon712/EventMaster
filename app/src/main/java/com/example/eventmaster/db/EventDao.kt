package com.example.eventmaster.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.eventmaster.model.EventData

@Dao
interface EventDao {

    @Query("SELECT * FROM EventData")
    fun getAllEvents() : LiveData<List<EventData>>

    @Insert
    fun addEvents(eventData: EventData)
}