package com.example.eventmaster.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eventmaster.model.CategoryData
import com.example.eventmaster.model.EventData

@Database(entities = [CategoryData::class, EventData::class], version = 2)
abstract class EventMasterDatabase : RoomDatabase(){
    companion object {
        const val NAME = "EventMaster_DB"
    }

    abstract fun getCategoryDao() : CategoryDao
    abstract fun getEventDao() : EventDao
}