package com.arnisand.remindermap.di.component

import android.app.Application
import com.arnisand.remindermap.di.module.AppModule
import com.arnisand.remindermap.di.module.MainActivityModule
import com.arnisand.remindermap.presentation.ReminderApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(reminderApp: ReminderApp)
}
