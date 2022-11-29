package com.example.lyceumapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lyceumapp.json.lessons.LessonJson

@Database(entities = [LessonJson::class],  version = 2)
abstract class LyceumDatabase: RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}