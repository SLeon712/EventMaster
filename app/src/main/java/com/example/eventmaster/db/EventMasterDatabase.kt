package com.example.eventmaster.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eventmaster.model.CategoryData

@Database(entities = [CategoryData::class], version = 1)
abstract class EventMasterDatabase : RoomDatabase(){
    companion object {
        const val NAME = "EventMaster_DB"
    }

    abstract fun getCategoryDao() : CategoryDao
}