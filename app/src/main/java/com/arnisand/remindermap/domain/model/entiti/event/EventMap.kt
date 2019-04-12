package com.arnisand.remindermap.domain.model.entiti.event

import androidx.room.*
import com.arnisand.remindermap.data.db.convertor.DateTypeConverter
import com.arnisand.remindermap.domain.model.values.Coordinate
import java.util.*

@Entity
@TypeConverters(DateTypeConverter::class)
data class EventMap(val title: String,
                    @Embedded val coordinate: Coordinate,
                    val date: Date,
                    @ColumnInfo(name = "time_from") val timeFrom: Date,
                    @ColumnInfo(name = "time_to") val timeTo: Date,
                    val description: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}