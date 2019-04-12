package com.arnisand.remindermap.presentation.event.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import com.arnisand.remindermap.domain.usecase.event.EventMapUseCase
import com.arnisand.remindermap.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateEventViewModel @Inject constructor(private val eventMapUseCase: EventMapUseCase): BaseViewModel() {

    private val _saveMarkerEvent = MutableLiveData<Long>()

    val saveMarkerEvent: LiveData<Long>
        get() = _saveMarkerEvent

    fun saveEventMap(eventMap: EventMap) {
        eventMapUseCase.saveEventMap(eventMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _saveMarkerEvent.value = it }
            .subscribe()
    }

}