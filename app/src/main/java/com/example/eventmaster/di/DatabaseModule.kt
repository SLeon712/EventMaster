package com.example.eventmaster.di

import android.content.Context
import androidx.room.Room
import com.example.eventmaster.db.CategoryDao
import com.example.eventmaster.db.EventDao
import com.example.eventmaster.db.EventMasterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
* DatabaseModule - módulo Hilt que provee Room y los DAOs.
* Con @Singleton nos aseguramos de que haya una sola instancia de la BD en toda la app.
*/
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EventMasterDatabase {
        return Room.databaseBuilder(
            context,
            EventMasterDatabase::class.java,
            EventMasterDatabase.NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideCategoryDao(db: EventMasterDatabase): CategoryDao = db.getCategoryDao()

    @Provides
    fun provideEventDao(db: EventMasterDatabase): EventDao = db.getEventDao()
}