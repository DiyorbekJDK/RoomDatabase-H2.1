package com.diyorbek.roomdatabase_h21.database

import androidx.room.TypeConverter
import java.util.*

class Convertors {
    @TypeConverter
    fun fromDateToLong(value: Date) : Long{
        return value.time
    }
    @TypeConverter
    fun fromLongToDate(value: Long): Date{
        return Date(value)
    }
}