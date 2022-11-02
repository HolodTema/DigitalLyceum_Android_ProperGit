package com.example.lyceumapp.database

import androidx.room.*
import com.example.lyceumapp.RequestManager

@Dao
interface LessonDao {
    @Insert
    fun insert(lessonDB: LessonDB)
    @Update
    fun update(lessonDB: LessonDB)
    @Delete
    fun delete(lessonDB: LessonDB)

    @Query("select exists(select * from lessondb)")
    fun areLessonsInDatabase(): Boolean

    @Query("select exists(select * from lessondb where schoolGradeId = :schoolGradeId)")
    fun areLessonsOfSchoolGradeIdInDatabase(schoolGradeId: Int): Boolean

    @Query("select * from lessondb")
    fun getAll(): List<LessonDB>

    @Query("select * from lessondb where schoolGradeId = :schoolGradeId")
    fun getBySchoolGradeId(schoolGradeId: Int): List<LessonDB>
}