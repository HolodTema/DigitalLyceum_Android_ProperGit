package com.example.lyceumapp.json.grades;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \"2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\"B-\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00042\b\b\u0003\u0010\u0005\u001a\u00020\u00042\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0007H\u00c6\u0001J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u0011\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0004H\u00d6\u0001J\b\u0010\u001c\u001a\u00020\u0007H\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0004H\u0016R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006#"}, d2 = {"Lcom/example/lyceumapp/json/grades/GradeJson;", "Landroid/os/Parcelable;", "", "id", "", "number", "letter", "", "gradeType", "(IILjava/lang/String;Ljava/lang/String;)V", "getGradeType", "()Ljava/lang/String;", "getId", "()I", "getLetter", "getNumber", "compareTo", "other", "component1", "component2", "component3", "component4", "copy", "describeContents", "equals", "", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "CREATOR", "app_debug"})
public final class GradeJson implements android.os.Parcelable, java.lang.Comparable<com.example.lyceumapp.json.grades.GradeJson> {
    private final int id = 0;
    private final int number = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String letter = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String gradeType = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.lyceumapp.json.grades.GradeJson.CREATOR CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.grades.GradeJson copy(@com.squareup.moshi.Json(name = "class_id")
    int id, @com.squareup.moshi.Json(name = "number")
    int number, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "letter")
    java.lang.String letter, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "class_type")
    java.lang.String gradeType) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    public GradeJson(@com.squareup.moshi.Json(name = "class_id")
    int id, @com.squareup.moshi.Json(name = "number")
    int number, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "letter")
    java.lang.String letter, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "class_type")
    java.lang.String gradeType) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getNumber() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLetter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGradeType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @java.lang.Override()
    public int compareTo(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.grades.GradeJson other) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/example/lyceumapp/json/grades/GradeJson$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/example/lyceumapp/json/grades/GradeJson;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/example/lyceumapp/json/grades/GradeJson;", "app_debug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<com.example.lyceumapp.json.grades.GradeJson> {
        
        private CREATOR() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.json.grades.GradeJson createFromParcel(@org.jetbrains.annotations.Nullable()
        android.os.Parcel parcel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.json.grades.GradeJson[] newArray(int size) {
            return null;
        }
    }
}