package com.arnisand.remindermap.presentation.event.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import com.arnisand.remindermap.domain.usecase.event.EventMapUseCase
import com.arnisand.remindermap.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailEventViewModel @Inject constructor(private val eventMapUseCase: EventMapUseCase): BaseViewModel() {

    private val _markerEvent = MutableLiveData<EventMap>()

    val markerEvent: LiveData<EventMap>
        get() = _markerEvent

    fun loadEventMap(it: Long) {
        eventMapUseCase.getEventMap(it)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _markerEvent.value = it }
            .subscribe()
    }

}