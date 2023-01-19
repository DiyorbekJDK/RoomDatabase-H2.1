package com.diyorbek.roomdatabase_h21.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ModuleTable")
data class ModuleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
): Parcelable
