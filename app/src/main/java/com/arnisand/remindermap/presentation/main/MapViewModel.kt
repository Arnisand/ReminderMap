package com.arnisand.remindermap.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import com.arnisand.remindermap.domain.usecase.event.EventMapUseCase
import com.arnisand.remindermap.presentation.base.BaseViewModel
import com.arnisand.remindermap.utils.livedata.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val eventMapUseCase: EventMapUseCase
) : BaseViewModel() {

    private val _allEventsMap = MutableLiveData<List<EventMap>>()
    private val _createdEventMap = SingleLiveEvent<EventMap>()
    private val _removedEventMap = SingleLiveEvent<Long>()

    val allEventsMap: LiveData<List<EventMap>>
        get() = _allEventsMap
    val createdEventMap: LiveData<EventMap>
        get() = _createdEventMap
    val removedEventMap: LiveData<Long>
        get() = _removedEventMap

    fun createEventMap(id: Long) {
        eventMapUseCase.getEventMap(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _createdEventMap.value = it
                }, {
                    Timber.d(it)
                }
            ).addTo(compositeDisposable)
    }

    fun getAllEventsMap() {
        eventMapUseCase.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _allEventsMap.value = it
            }
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun removeEventMap(id: Long) {
        eventMapUseCase.deleteEventMap(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _removedEventMap.value = id
            }, {
                Timber.d(it)
            })
            .addTo(compositeDisposable)
    }
}