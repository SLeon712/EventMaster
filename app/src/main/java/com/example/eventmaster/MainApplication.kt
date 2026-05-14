package com.example.eventmaster

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import com.example.eventmaster.db.EventMasterDatabase

@HiltAndroidApp
class MainApplication : Application() {

    companion object {
        lateinit var eventMasterDatabase: EventMasterDatabase
    }

    override fun onCreate() {
        super.onCreate()
        eventMasterDatabase = Room.databaseBuilder(
            applicationContext,
            EventMasterDatabase::class.java,
            EventMasterDatabase.NAME
        ).fallbackToDestructiveMigration().build()
    }
}