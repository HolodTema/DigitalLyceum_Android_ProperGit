package com.example.lyceumapp.database

import android.content.Context
import androidx.room.Room
import com.example.lyceumapp.Const

object DatabaseClient {
    private var instance: LyceumDatabase? = null

    fun getInstance(context: Context) = instance ?: Room.databaseBuilder(context, LyceumDatabase::class.java, Const.DB_NAME).build()

}