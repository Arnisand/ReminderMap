package com.arnisand.remindermap.di.module

import com.arnisand.remindermap.presentation.event.create.CreateEventDialog
import com.arnisand.remindermap.presentation.event.detail.DetailEventDialog
import com.arnisand.remindermap.presentation.main.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateEventDialog(): CreateEventDialog

    @ContributesAndroidInjector
    abstract fun contributeDetailEventDialog(): DetailEventDialog
}
