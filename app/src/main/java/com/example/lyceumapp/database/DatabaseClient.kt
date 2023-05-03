package com.example.lyceumapp.database

import android.content.Context
import androidx.room.Room
import com.example.lyceumapp.const

object DatabaseClient {
    private var instance: LyceumDatabase? = null

    fun getInstance(context: Context) = instance ?: Room.databaseBuilder(context, LyceumDatabase::class.java, const.DB_NAME).build()

}