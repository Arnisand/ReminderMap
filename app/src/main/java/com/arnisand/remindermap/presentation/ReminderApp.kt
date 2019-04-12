package com.arnisand.remindermap.presentation

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.arnisand.remindermap.BuildConfig
import com.arnisand.remindermap.di.component.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ReminderApp : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        if(BuildConfig.DEBUG) LeakCanary.install(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityDispatchingAndroidInjector
}