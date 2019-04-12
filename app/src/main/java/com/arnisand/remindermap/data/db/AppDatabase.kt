package com.arnisand.remindermap.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import com.arnisand.remindermap.domain.model.entiti.event.EventMapDao

@Database(entities = [EventMap::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventMapDao(): EventMapDao
}
