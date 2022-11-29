package com.example.lyceumapp.database

import androidx.room.*
import com.example.lyceumapp.json.lessons.LessonJson

@Dao
interface LessonDao {
    @Insert
    fun insert(lessonJson: LessonJson)
    @Update
    fun update(lessonJson: LessonJson)
    @Delete
    fun delete(lessonJson: LessonJson)

    @Query("select exists(select * from lessonjson)")
    fun areLessonsInDatabase(): Boolean

    @Query("select * from lessonjson")
    fun getAll(): List<LessonJson>

    @Query("delete from lessonjson")
    fun deleteAll()
}