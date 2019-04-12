package com.arnisand.remindermap.di.module

import com.arnisand.remindermap.data.db.AppDatabase
import com.arnisand.remindermap.data.repositories.event.EventMapRepository
import com.arnisand.remindermap.data.repositories.event.EventMapRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesEventMapRepository(database: AppDatabase): EventMapRepository = EventMapRepositoryImpl(database)
}