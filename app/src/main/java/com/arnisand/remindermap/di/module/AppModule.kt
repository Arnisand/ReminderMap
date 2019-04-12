package com.arnisand.remindermap.di.module

import android.app.Application
import androidx.room.Room
import com.arnisand.remindermap.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, RepositoryModule::class, UseCaseModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "reminder_database"
        ).fallbackToDestructiveMigration().build()
    }
}