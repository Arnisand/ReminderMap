package com.arnisand.remindermap.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arnisand.remindermap.di.factory.ViewModelFactory
import com.arnisand.remindermap.presentation.event.create.CreateEventViewModel
import com.arnisand.remindermap.presentation.event.detail.DetailEventViewModel
import com.arnisand.remindermap.presentation.main.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(mapViewModel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateEventViewModel::class)
    abstract fun bindCreateEventViewModel(createEventViewModel: CreateEventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailEventViewModel::class)
    abstract fun bindDetailEventViewModel(detailEventViewModel: DetailEventViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}