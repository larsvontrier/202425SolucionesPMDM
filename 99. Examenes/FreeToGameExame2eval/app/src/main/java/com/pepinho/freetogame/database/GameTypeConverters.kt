package com.pepinho.freetogame.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class GameTypeConverters {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestampToLocalDate(millis: Long): LocalDate {
        return Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateToTimestamp(localDate: LocalDate): Long {
        return localDate.atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }
}