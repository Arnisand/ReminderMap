package com.arnisand.remindermap.data.repositories.event

import com.arnisand.remindermap.data.db.AppDatabase
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import io.reactivex.Completable
import io.reactivex.Single

class EventMapRepositoryImpl(database: AppDatabase) : EventMapRepository {

    private val eventMapDao = database.eventMapDao()

    override fun getEventMap(id: Long): Single<EventMap> {
        return Single.fromCallable {
            eventMapDao.getEventMap(id)
        }
    }

    override fun getAll(): Single<List<EventMap>> {
        return Single.fromCallable {
            eventMapDao.getAll()
        }
    }

    override fun saveEventMap(eventMap: EventMap): Single<Long> {
        return Single.fromCallable {
            eventMapDao.insert(eventMap)
        }
    }

    override fun deleteEventMap(id: Long): Completable {
        return Completable.fromCallable {
            eventMapDao.delete(id)
        }
    }
}