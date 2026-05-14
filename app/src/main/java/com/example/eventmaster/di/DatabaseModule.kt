package com.example.eventmaster.di

import android.content.Context
import androidx.room.Room
import com.example.eventmaster.db.EventMasterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EventMasterDatabase {
        return Room.databaseBuilder(
            context,
            EventMasterDatabase::class.java,
            "EventMaster_DB"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideCategoryDao(db: EventMasterDatabase) = db.getCategoryDao()

    @Provides
    fun provideEventDao(db: EventMasterDatabase) = db.getEventDao()
}