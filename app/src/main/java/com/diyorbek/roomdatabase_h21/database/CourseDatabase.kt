package com.diyorbek.roomdatabase_h21.database

import android.content.Context
import androidx.room.*

@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
@TypeConverters(NamesTypeConverter::class)
abstract class CourseDatabase : RoomDatabase() {
    abstract val dao: CourseDao

    companion object {
        @Volatile
        private var instance: CourseDatabase? = null

        operator fun invoke(context: Context): CourseDatabase {
            return instance ?: synchronized(Any()) {
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun createDatabase(context: Context): CourseDatabase {
            return Room.databaseBuilder(
                context,
                CourseDatabase::class.java,
                "Course.db"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}