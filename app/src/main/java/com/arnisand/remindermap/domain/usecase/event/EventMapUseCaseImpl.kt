package com.arnisand.remindermap.domain.usecase.event

import com.arnisand.remindermap.data.repositories.event.EventMapRepository
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import io.reactivex.Completable
import io.reactivex.Single

class EventMapUseCaseImpl(private val eventMapRepository: EventMapRepository) : EventMapUseCase {

    override fun getEventMap(id: Long): Single<EventMap> {
        return eventMapRepository.getEventMap(id)
    }

    override fun getAll(): Single<List<EventMap>> {
        return eventMapRepository.getAll()
    }

    override fun saveEventMap(eventMap: EventMap): Single<Long> {
        return eventMapRepository.saveEventMap(eventMap)
    }

    override fun deleteEventMap(id: Long): Completable {
        return eventMapRepository.deleteEventMap(id)
    }
}