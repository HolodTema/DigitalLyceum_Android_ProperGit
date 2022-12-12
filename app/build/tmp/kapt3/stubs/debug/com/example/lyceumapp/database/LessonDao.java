package com.example.lyceumapp.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\'J\b\u0010\b\u001a\u00020\u0005H\'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\'J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\'\u00a8\u0006\r"}, d2 = {"Lcom/example/lyceumapp/database/LessonDao;", "", "areLessonsInDatabase", "", "delete", "", "lessonJson", "Lcom/example/lyceumapp/json/lessons/LessonJson;", "deleteAll", "getAll", "", "insert", "update", "app_debug"})
public abstract interface LessonDao {
    
    @androidx.room.Insert()
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.lessons.LessonJson lessonJson);
    
    @androidx.room.Update()
    public abstract void update(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.lessons.LessonJson lessonJson);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.lessons.LessonJson lessonJson);
    
    @androidx.room.Query(value = "select exists(select * from lessonjson)")
    public abstract boolean areLessonsInDatabase();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from lessonjson")
    public abstract java.util.List<com.example.lyceumapp.json.lessons.LessonJson> getAll();
    
    @androidx.room.Query(value = "delete from lessonjson")
    public abstract void deleteAll();
}