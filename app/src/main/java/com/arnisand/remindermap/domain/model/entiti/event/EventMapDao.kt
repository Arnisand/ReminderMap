package com.arnisand.remindermap.domain.model.entiti.event

import androidx.room.Dao
import androidx.room.Query
import com.arnisand.remindermap.data.db.base.BaseDao

@Dao
interface EventMapDao : BaseDao<EventMap> {

    @Query("SELECT * FROM eventmap")
    fun getAll(): List<EventMap>

    @Query("SELECT * FROM eventmap WHERE id = :id")
    fun getEventMap(id: Long): EventMap

    @Query("DELETE FROM eventmap WHERE id = :id")
    fun delete(id: Long)
}