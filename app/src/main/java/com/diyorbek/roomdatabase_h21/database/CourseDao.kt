package com.diyorbek.roomdatabase_h21.database

import androidx.room.*

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCourse(courseEntity: CourseEntity)

    @Query("SELECT * FROM CourseTable ORDER BY id DESC")
    fun getAllCourses(): List<CourseEntity>

    @Update
    fun updateCourse(courseEntity: CourseEntity)

    @Delete
    fun deleteCourse(courseEntity: CourseEntity)
}