package com.example.lyceumapp.json.lessons;

import java.lang.System;

@androidx.room.Entity()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 *2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001*BK\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0004\u0012\b\b\u0001\u0010\f\u001a\u00020\u0004\u0012\b\b\u0001\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0011\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0000H\u0096\u0002J\b\u0010$\u001a\u00020\u0004H\u0016J\u0018\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0004H\u0016R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015\u00a8\u0006+"}, d2 = {"Lcom/example/lyceumapp/json/lessons/LessonJson;", "Landroid/os/Parcelable;", "", "id", "", "name", "", "startTime", "Lcom/example/lyceumapp/json/lessons/LessonStartTimeInterval;", "endTime", "Lcom/example/lyceumapp/json/lessons/LessonEndTimeInterval;", "week", "weekday", "teacher", "Lcom/example/lyceumapp/json/teachers/TeacherJson;", "(ILjava/lang/String;Lcom/example/lyceumapp/json/lessons/LessonStartTimeInterval;Lcom/example/lyceumapp/json/lessons/LessonEndTimeInterval;IILcom/example/lyceumapp/json/teachers/TeacherJson;)V", "getEndTime", "()Lcom/example/lyceumapp/json/lessons/LessonEndTimeInterval;", "getId", "()I", "setId", "(I)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getStartTime", "()Lcom/example/lyceumapp/json/lessons/LessonStartTimeInterval;", "getTeacher", "()Lcom/example/lyceumapp/json/teachers/TeacherJson;", "getWeek", "setWeek", "getWeekday", "setWeekday", "compareTo", "other", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "p1", "CREATOR", "app_debug"})
public final class LessonJson implements android.os.Parcelable, java.lang.Comparable<com.example.lyceumapp.json.lessons.LessonJson> {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "lesson_id")
    private int id;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "lesson_name")
    private java.lang.String name;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Embedded()
    private final com.example.lyceumapp.json.lessons.LessonStartTimeInterval startTime = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Embedded()
    private final com.example.lyceumapp.json.lessons.LessonEndTimeInterval endTime = null;
    private int week;
    private int weekday;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Embedded()
    private final com.example.lyceumapp.json.teachers.TeacherJson teacher = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.lyceumapp.json.lessons.LessonJson.CREATOR CREATOR = null;
    
    public LessonJson(@com.squareup.moshi.Json(name = "lesson_id")
    int id, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "name")
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "start_time")
    com.example.lyceumapp.json.lessons.LessonStartTimeInterval startTime, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "end_time")
    com.example.lyceumapp.json.lessons.LessonEndTimeInterval endTime, @com.squareup.moshi.Json(name = "week")
    int week, @com.squareup.moshi.Json(name = "weekday")
    int weekday, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "teacher")
    com.example.lyceumapp.json.teachers.TeacherJson teacher) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    public final void setId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.lessons.LessonStartTimeInterval getStartTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.lessons.LessonEndTimeInterval getEndTime() {
        return null;
    }
    
    public final int getWeek() {
        return 0;
    }
    
    public final void setWeek(int p0) {
    }
    
    public final int getWeekday() {
        return 0;
    }
    
    public final void setWeekday(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.teachers.TeacherJson getTeacher() {
        return null;
    }
    
    @java.lang.Override()
    public int compareTo(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.lessons.LessonJson other) {
        return 0;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int p1) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/example/lyceumapp/json/lessons/LessonJson$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/example/lyceumapp/json/lessons/LessonJson;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "p0", "", "(I)[Lcom/example/lyceumapp/json/lessons/LessonJson;", "app_debug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<com.example.lyceumapp.json.lessons.LessonJson> {
        
        private CREATOR() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.json.lessons.LessonJson createFromParcel(@org.jetbrains.annotations.Nullable()
        android.os.Parcel parcel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.json.lessons.LessonJson[] newArray(int p0) {
            return null;
        }
    }
}