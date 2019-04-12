package com.arnisand.remindermap.data.db.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: Array<out T>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<T>): LongArray

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(model: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(models: Array<out T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(models: List<T>)

    @Delete
    fun delete(model: T)

    @Delete
    fun delete(model: Array<out T>)

    @Delete
    fun delete(models: List<T>)
}