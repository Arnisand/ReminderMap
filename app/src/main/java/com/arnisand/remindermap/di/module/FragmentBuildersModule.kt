package com.arnisand.remindermap.di.module

import com.arnisand.remindermap.presentation.main.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMapFragment(): MapFragment
}
