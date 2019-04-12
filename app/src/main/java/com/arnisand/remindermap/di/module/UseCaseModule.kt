package com.arnisand.remindermap.di.module

import com.arnisand.remindermap.data.repositories.event.EventMapRepository
import com.arnisand.remindermap.domain.usecase.event.EventMapUseCase
import com.arnisand.remindermap.domain.usecase.event.EventMapUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun providesEventMapUseCase(eventMapRepository: EventMapRepository): EventMapUseCase = EventMapUseCaseImpl(eventMapRepository)
}