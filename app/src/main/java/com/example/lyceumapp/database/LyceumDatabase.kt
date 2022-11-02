package com.example.lyceumapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LessonDB::class], version = 2)
abstract class LyceumDatabase: RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}